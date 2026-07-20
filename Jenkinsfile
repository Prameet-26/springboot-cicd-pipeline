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

        stage('OWASP Dependency-Check') {
            steps {
                sh '''
                    /var/jenkins_home/dependency-check/bin/dependency-check.sh \
                        --project "SpringBoot-CICD" \
                        --scan pom.xml \
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

        stage('Deploy') {
            steps {
                echo "Deployment stage placeholder."
                echo "Kubernetes deployment will be added later."
            }
        }
    }

    post {

        success {
            echo "🎉 CI/CD Pipeline SUCCESS"
        }

        failure {
            echo "❌ CI/CD Pipeline FAILED"
        }

        always {
            cleanWs()
        }
    }
}
