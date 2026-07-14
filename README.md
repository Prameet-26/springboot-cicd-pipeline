# 🚀 Spring Boot CI/CD Pipeline with Jenkins, SonarQube, Docker, Prometheus & Grafana on AWS EC2

## 📌 Project Overview

This project demonstrates a complete DevOps CI/CD pipeline for deploying a Spring Boot application using modern DevOps tools and AWS cloud infrastructure.

The pipeline automates the software delivery lifecycle, starting from source code checkout and ending with application monitoring and alerting. Every code change pushed to GitHub triggers a Jenkins pipeline that builds the application, executes unit tests, performs static code analysis using SonarQube, builds a Docker image, and pushes the image to Docker Hub. The application is then deployed on an AWS EC2 instance, where it is continuously monitored using Prometheus and visualized through Grafana dashboards. Alertmanager is configured to send email notifications whenever the application becomes unavailable.

This project was built to gain hands-on experience with real-world DevOps practices, including Continuous Integration (CI), Continuous Delivery (CD), Infrastructure Monitoring, Containerization, and Cloud Deployment.

## 🎯 Project Objectives

* Automate the build, test, and deployment process using Jenkins.
* Improve code quality with SonarQube static code analysis.
* Package the application as a Docker container.
* Store Docker images in Docker Hub.
* Deploy and run the application on an AWS EC2 instance.
* Monitor application health and metrics using Prometheus.
* Visualize system and application metrics with Grafana.
* Configure Alertmanager to send email notifications for application downtime.
* Follow DevOps best practices by integrating multiple tools into a single automated workflow.

## ✨ Key Features

* Fully automated Jenkins CI/CD pipeline
* Spring Boot application built with Maven
* GitHub integration for source code management
* Automated unit testing
* SonarQube code quality analysis
* Docker image creation and publishing to Docker Hub
* Deployment on AWS EC2 (Ubuntu)
* Prometheus metrics collection
* Grafana monitoring dashboards
* Alertmanager email notifications
* End-to-end DevOps workflow demonstrating CI, CD, Monitoring, and Alerting

## 🏗️ Project Architecture

The following architecture illustrates the complete CI/CD workflow implemented in this project.

```text
                         Developer
                             │
                             ▼
                    GitHub Repository
                             │
                   (Code Push / Commit)
                             │
                             ▼
                  Jenkins CI/CD Pipeline
                             │
     ┌───────────────────────┼────────────────────────┐
     │                       │                        │
     ▼                       ▼                        ▼
Checkout Code         Maven Build & Test      SonarQube Analysis
     │
     ▼
Build Docker Image
     │
     ▼
Push Image to Docker Hub
     │
     ▼
Deploy Docker Container on AWS EC2
     │
     ▼
Spring Boot Application
     │
     ▼
Micrometer Actuator Metrics
     │
     ▼
Prometheus Server
     │
     ▼
Grafana Dashboard
     │
     ▼
Alertmanager
     │
     ▼
Email Notification
```

### 🔄 Workflow Explanation

1. The developer pushes source code changes to the GitHub repository.
2. Jenkins detects the latest code and starts the CI/CD pipeline.
3. Jenkins checks out the source code from GitHub.
4. Maven compiles the application and executes unit tests.
5. SonarQube performs static code analysis to identify bugs, vulnerabilities, and code smells.
6. Jenkins builds a Docker image using the project's Dockerfile.
7. The Docker image is pushed to Docker Hub for version management.
8. The Docker container runs the Spring Boot application on an AWS EC2 instance.
9. Spring Boot exposes application metrics through Micrometer and the Actuator endpoint.
10. Prometheus continuously scrapes metrics from the application.
11. Grafana visualizes the collected metrics through interactive dashboards.
12. Alertmanager sends email notifications whenever the application becomes unavailable or an alert rule is triggered.

## 🛠️ Tech Stack

| Category           | Technology                        | Purpose                                           |
| ------------------ | --------------------------------- | ------------------------------------------------- |
| Language           | Java 17                           | Spring Boot application development               |
| Framework          | Spring Boot 3                     | REST API development                              |
| Build Tool         | Maven                             | Build automation and dependency management        |
| Version Control    | Git                               | Source code versioning                            |
| Repository         | GitHub                            | Source code hosting                               |
| CI/CD              | Jenkins                           | Automated build, test, and deployment pipeline    |
| Code Quality       | SonarQube                         | Static code analysis and quality checks           |
| Containerization   | Docker                            | Package the application into a portable container |
| Container Registry | Docker Hub                        | Store and distribute Docker images                |
| Cloud Platform     | AWS EC2 (Ubuntu)                  | Host the application and DevOps tools             |
| Monitoring         | Prometheus                        | Collect application and system metrics            |
| Visualization      | Grafana                           | Create monitoring dashboards                      |
| Alerting           | Alertmanager                      | Send email alerts when issues are detected        |
| Metrics            | Spring Boot Actuator & Micrometer | Expose application metrics to Prometheus          |


## 📂 Project Structure

```text
springboot-cicd-pipeline/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── README.md
├── .gitignore
└── screenshots/
    ├── architecture-diagram.png
    ├── 01-github-home.png
    ├── 02-project-structure.png
    ├── 03-springboot-running.png
    ├── 04-jenkins-dashboard.png
    ├── 05-pipeline-success.png
    ├── 06-stage-view.png
    ├── 07-sonarqube-dashboard.png
    ├── 08-docker-images.png
    ├── 09-docker-containers.png
    ├── 10-dockerhub.png
    ├── 11-prometheus-targets.png
    ├── 12-prometheus-alerts.png
    ├── 13-grafana-dashboard.png
    ├── 14-alertmanager.png
    ├── 15-email-alert.png
    └── 16-ec2-instance.png
```
## 🚀 CI/CD Pipeline Workflow

The Jenkins pipeline automates the complete software delivery lifecycle, from source code checkout to application deployment on AWS EC2. Whenever changes are pushed to the GitHub repository, Jenkins executes the following stages:

| Stage                 | Description                                                                                                  |
| --------------------- | ------------------------------------------------------------------------------------------------------------ |
| 📥 Checkout           | Retrieves the latest source code from the GitHub repository.                                                 |
| 🔨 Build              | Compiles the Spring Boot application using Maven.                                                            |
| ✅ Test                | Executes unit tests to verify application functionality.                                                     |
| 🔍 SonarQube Analysis | Performs static code analysis to identify bugs, vulnerabilities, and code smells.                            |
| 🐳 Docker Build       | Builds a Docker image using the project's Dockerfile.                                                        |
| 🔑 Docker Login       | Authenticates securely with Docker Hub using Jenkins credentials.                                            |
| 📤 Docker Push        | Pushes the newly built Docker image to Docker Hub.                                                           |
| 🚀 Deployment         | Pulls the latest Docker image and automatically deploys the Spring Boot application on the AWS EC2 instance. |
| 📊 Monitoring         | Prometheus continuously collects metrics from the running application.                                       |
| 📈 Visualization      | Grafana displays application and system metrics through interactive dashboards.                              |
| 🚨 Alerting           | Alertmanager monitors alert rules and sends email notifications when issues are detected.                    |


### Pipeline Flow

```text
Developer
    │
    ▼
Push Code to GitHub
    │
    ▼
Jenkins Pipeline Triggered
    │
    ▼
Checkout Source Code
    │
    ▼
Build Application (Maven)
    │
    ▼
Run Unit Tests
    │
    ▼
SonarQube Analysis
    │
    ▼
Build Docker Image
    │
    ▼
Push Image to Docker Hub
    │
    ▼
Deploy on AWS EC2
    │
    ▼
Spring Boot Application Running
    │
    ▼
Prometheus Monitoring
    │
    ▼
Grafana Dashboard
    │
    ▼
Alertmanager
    │
    ▼
Email Notification
```
