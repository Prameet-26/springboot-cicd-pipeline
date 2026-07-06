pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "prameet26"
        IMAGE_NAME = "springboot-cicd"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw clean package
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    ./mvnw test
                '''
            }
        }

        stage('Docker Info') {
            steps {
                sh '''
                    echo "Current User:"
                    whoami

                    echo "Docker Location:"
                    which docker

                    echo "Docker Version:"
pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "prameet26"
        IMAGE_NAME = "springboot-cicd"
        IMAGE_TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "========== CHECKOUT =========="
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "========== BUILD =========="
                sh '''
                    chmod +x mvnw
                    ./mvnw clean package -DskipTests
                '''
            }
        }

        stage('Test') {
            steps {
                echo "========== TEST =========="
                sh '''
                    ./mvnw test
                '''
            }
        }

        stage('Docker Diagnostics') {
            steps {
                echo "========== DOCKER INFO =========="
                sh '''
                    set -x

                    echo "Current User:"
                    whoami

                    echo "Current Directory:"
                    pwd

                    echo "PATH:"
                    echo $PATH

                    echo "Docker Location:"
                    which docker || true

                    echo "Docker Binary:"
                    ls -l /usr/bin/docker || true

                    echo "Docker Version:"
                    docker --version

                    echo "Docker Info:"
                    docker info
                '''
            }
        }

        stage('Docker Build') {
            steps {
                echo "========== BUILD IMAGE =========="
                sh '''
                    docker build \
                    -t ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        stage('Docker Login') {
            steps {
                echo "========== DOCKER LOGIN =========="

                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-cred',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''
                        echo "$DOCKER_PASS" | docker login \
                        -u "$DOCKER_USER" \
                        --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                echo "========== PUSH IMAGE =========="
                sh '''
                    docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Cleanup') {
            steps {
                echo "========== CLEANUP =========="
                sh '''
                    docker image prune -f || true
                '''
            }
        }
    }

    post {

        success {
            echo "🎉 Pipeline completed successfully!"
        }

        failure {
            echo "❌ Pipeline failed!"
        }

        always {
            cleanWs(deleteDirs: true)
        }
    }
}
