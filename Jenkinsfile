pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "prameet26"
        IMAGE_NAME = "springboot-cicd"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'dev',
                    credentialsId: 'github-pat',
                    url: 'https://github.com/Prameet-26/springboot-cicd-pipeline.git'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Debug Docker') {
            steps {
                sh '''
                    echo "===== USER ====="
                    whoami

                    echo "===== PATH ====="
                    echo $PATH

                    echo "===== PWD ====="
                    pwd

                    echo "===== WHICH DOCKER ====="
                    which docker || true

                    echo "===== COMMAND ====="
                    command -v docker || true

                    echo "===== DOCKER VERSION ====="
                    docker --version || true

                    echo "===== DOCKER SOCKET ====="
                    ls -l /var/run/docker.sock || true

                    echo "===== DOCKER BINARY ====="
                    ls -l /usr/bin/docker || true
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    docker build -t ${DOCKERHUB_USER}/${IMAGE_NAME}:latest .
                '''
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-cred',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                    docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:latest
                '''
            }
        }
    }

    post {
        success {
            echo "✅ CI/CD Pipeline SUCCESS"
        }

        failure {
            echo "❌ CI/CD Pipeline FAILED"
        }

        always {
            cleanWs()
        }
    }
}
