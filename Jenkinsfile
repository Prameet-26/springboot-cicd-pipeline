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
                    chmod +x mvnw
                    ./mvnw test
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                        chmod +x mvnw
                        ./mvnw sonar:sonar \
                          -Dsonar.projectKey=springboot-cicd \
                          -Dsonar.projectName=springboot-cicd
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Info') {
            steps {
                sh '''
                    echo "Current User:"
                    whoami

                    echo "Current Directory:"
                    pwd

                    echo "PATH:"
                    echo $PATH

                    echo "Docker Version:"
                    /usr/bin/docker --version
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    /usr/bin/docker build -t ${DOCKERHUB_USER}/${IMAGE_NAME}:latest .
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
                        echo "$DOCKER_PASS" | /usr/bin/docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                    /usr/bin/docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:latest
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    /usr/bin/docker stop springboot-cicd || true
                    /usr/bin/docker rm springboot-cicd || true

                    /usr/bin/docker pull ${DOCKERHUB_USER}/${IMAGE_NAME}:latest

                    /usr/bin/docker run -d \
                        --name springboot-cicd \
                        -p 8081:8080 \
                        ${DOCKERHUB_USER}/${IMAGE_NAME}:latest
                '''
            }
        }
    }

    post {

        success {
            echo '🎉 CI/CD Pipeline SUCCESS'
        }

        failure {
            echo '❌ CI/CD Pipeline FAILED'
        }

        always {
            cleanWs()
        }
    }
}
