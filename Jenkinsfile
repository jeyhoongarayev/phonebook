pipeline {
environment {
    imagename = "ceyhunqarayev/phonebookfinal:latest"
    dockerImage = ''
  }
    agent any
    stages {
        stage ('Compile') {
            steps {
                withMaven(maven : 'maven_3') {
                    sh 'mvn clean install'
                }
            }
        }
        stage ('Unit Tests') {
            steps {
                withMaven(maven : 'maven_3') {
                    sh 'mvn test'
                }
            }
        }
        stage ('SonarQube analysis') {
            steps {
                withMaven(maven : 'maven_3') {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean verify sonar:sonar'
                    }
                }
                waitForQualityGate abortPipeline: true
            }
        }
        stage('Build Docker Image') {
            steps {
                 script {
                    dockerImage = docker.build imagename
                 }
            }
        }
        stage('Push Docker Image') {
            steps {
                 script {
                     withCredentials([string(credentialsId: 'Docker-Hub-Password', variable: 'dockerhubpwd')]) {
                         sh 'docker login -u ceyhunqarayev -p dockerhubpwd'
                     }
                     dockerImage.push('latest')
                 }
            }
        }
        stage('Deploy on K8S') {
            steps {
                script {
                   sh 'kubectl rollout restart deployment/backend-final -n backend-app-final'
                }
            }
        }
    }
}