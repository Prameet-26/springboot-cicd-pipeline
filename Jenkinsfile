pipeline {
    agent any

    environment {
        IMAGE_NAME = "springboot-cicd"
        DOCKERHUB_USER = "prameet26"   // 🔥 replace with your DockerHub username
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'dev',
                url: 'https://github.com/Prameet-26/springboot-cicd-pipeline.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
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
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push $DOCKERHUB_USER/$IMAGE_NAME:latest'
            }
        }
    }

    post {
        success {
            echo '🚀 CI/CD Pipeline SUCCESS'
        }
        failure {
            echo '❌ CI/CD Pipeline FAILED'
        }
    }
}
