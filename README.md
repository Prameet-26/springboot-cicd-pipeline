# Spring Boot CI/CD Pipeline

# 🚀 Spring Boot CI/CD Pipeline with Jenkins, Docker, SonarQube, Prometheus & Grafana

## 📌 Project Overview

This project demonstrates a complete DevOps CI/CD pipeline for deploying and monitoring a Spring Boot application on AWS EC2.

The pipeline automates code checkout, application build, testing, code quality analysis, Docker image creation, image publishing, and application deployment. It also includes a complete monitoring and alerting stack using Prometheus, Grafana, Alertmanager, Micrometer, and Node Exporter.

---

# 🏗️ Architecture

```
                    GitHub Repository
                           │
                           ▼
                     Jenkins Pipeline
                           │
          ┌────────────────┼────────────────┐
          ▼                ▼                ▼
     Build (Maven)     Unit Tests      SonarQube Scan
                           │
                           ▼
                    Docker Image Build
                           │
                           ▼
                     Push to DockerHub
                           │
                           ▼
                 AWS EC2 Docker Host
                           │
                  Spring Boot Container
                           │
          ┌────────────────┴────────────────┐
          ▼                                 ▼
    Micrometer Metrics               Node Exporter
          │                                 │
          └──────────────┬──────────────────┘
                         ▼
                    Prometheus
                         │
            ┌────────────┴────────────┐
            ▼                         ▼
      Alertmanager               Grafana
            │                         │
            ▼                         ▼
       Email Alerts           Live Dashboards
```

---

# ✨ Features

* Automated CI/CD pipeline using Jenkins
* Source code management with GitHub
* Maven build automation
* Unit testing during pipeline execution
* SonarQube code quality analysis
* Docker image creation
* DockerHub integration
* Deployment on AWS EC2
* Spring Boot Actuator metrics
* Micrometer integration
* Prometheus monitoring
* Grafana dashboards
* Node Exporter for Linux server monitoring
* Alertmanager email notifications
* Container monitoring with cAdvisor integration

---

# 🛠️ Technology Stack

| Category             | Technologies  |
| -------------------- | ------------- |
| Language             | Java 21       |
| Framework            | Spring Boot   |
| Build Tool           | Maven         |
| Version Control      | Git, GitHub   |
| CI/CD                | Jenkins       |
| Code Quality         | SonarQube     |
| Containerization     | Docker        |
| Container Registry   | DockerHub     |
| Cloud Platform       | AWS EC2       |
| Monitoring           | Prometheus    |
| Visualization        | Grafana       |
| Metrics              | Micrometer    |
| Server Monitoring    | Node Exporter |
| Alerting             | Alertmanager  |
| Container Monitoring | cAdvisor      |

---

# 🔄 CI/CD Pipeline Workflow

1. Developer pushes code to GitHub.
2. Jenkins detects the latest changes.
3. Source code is checked out.
4. Maven compiles the application.
5. Unit tests are executed.
6. SonarQube performs static code analysis.
7. Docker image is built.
8. Docker image is pushed to DockerHub.
9. Docker container is deployed on AWS EC2.
10. Spring Boot exposes metrics through Micrometer.
11. Prometheus scrapes application and infrastructure metrics.
12. Grafana visualizes metrics.
13. Alertmanager sends email alerts when predefined conditions are met.

---

# 📊 Monitoring Stack

### Application Monitoring

* Spring Boot Actuator
* Micrometer
* JVM Metrics
* HTTP Metrics
* Application Health

### Infrastructure Monitoring

* CPU Usage
* Memory Usage
* Disk Usage
* Filesystem
* Network Usage
* System Load

### Container Monitoring

* Docker Container Metrics
* CPU Usage
* Memory Usage
* Network Traffic
* Filesystem Metrics

---

# 📧 Alerting

Alertmanager is configured to send email notifications whenever:

* Spring Boot application becomes unavailable
* Prometheus alert rules are triggered

Example alert:

```
[FIRING:1] SpringBootAppDown
```

---

# 📁 Project Structure

```
springboot-cicd-pipeline
│
├── src/
├── pom.xml
├── Dockerfile
├── Jenkinsfile
├── README.md
│
├── monitoring/
│   ├── prometheus.yml
│   ├── alert.rules.yml
│   └── alertmanager.yml
│
├── screenshots/
│   ├── jenkins-success.png
│   ├── sonarqube-dashboard.png
│   ├── prometheus-targets.png
│   ├── grafana-dashboard.png
│   └── alert-email.png
│
└── docs/
```

---

# 🚀 Running the Project

## Clone Repository

```bash
git clone https://github.com/Prameet-26/springboot-cicd-pipeline.git
```

## Build

```bash
mvn clean package
```

## Build Docker Image

```bash
docker build -t springboot-cicd .
```

## Run Container

```bash
docker run -d -p 8081:8080 springboot-cicd
```

---

# 📈 Monitoring URLs

| Service      | URL                |
| ------------ | ------------------ |
| Spring Boot  | http://EC2-IP:8081 |
| Jenkins      | http://EC2-IP:8080 |
| SonarQube    | http://EC2-IP:9000 |
| Prometheus   | http://EC2-IP:9090 |
| Grafana      | http://EC2-IP:3000 |
| Alertmanager | http://EC2-IP:9093 |

---

# 📸 Screenshots

Add screenshots for:

* Jenkins Successful Pipeline
* SonarQube Dashboard
* Prometheus Targets
* Grafana Dashboard
* Spring Boot Application
* Email Alert Notification

---

# 🎯 Future Improvements

* Kubernetes deployment
* Helm charts
* Terraform infrastructure provisioning
* GitHub Actions pipeline
* Loki log aggregation
* Promtail integration
* Grafana Alerting
* AWS CloudWatch integration

---

# 👨‍💻 Author

**Prameet Kumar**

B.Tech 

DevOps | AWS | Docker | Jenkins | Kubernetes | Terraform | Monitoring | CI/CD
