pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "prameet26"
        IMAGE_NAME = "springboot-cicd"
        AWS_PAGER = ""
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

        stage('OWASP Dependency-Check') {
            steps {
                sh '''
                    /var/jenkins_home/dependency-check/bin/dependency-check.sh \
                        --project "SpringBoot-CICD" \
                        --scan . \
                        --format HTML \
                        --out dependency-check-report
                '''
            }
        }

        stage('Publish OWASP Report') {
            steps {
                archiveArtifacts artifacts: 'dependency-check-report/**/*', fingerprint: true
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
                sh 'docker version'
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
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-cred',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
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

        stage('Deploy to Amazon EKS') {
            steps {
                sh '''
                    kubectl set image deployment/springboot-app \
                    springboot-container=${DOCKERHUB_USER}/${IMAGE_NAME}:latest

                    kubectl rollout status deployment/springboot-app

                    kubectl get deployment -o wide
                    kubectl get pods -o wide
                    kubectl get svc
                '''
            }
        }
    }

    post {

        success {
            echo "🎉 Spring Boot DevSecOps CI/CD Pipeline SUCCESS"
        }

        failure {
            echo "❌ Spring Boot DevSecOps CI/CD Pipeline FAILED"
        }

        always {
            cleanWs()
        }
    }
}
