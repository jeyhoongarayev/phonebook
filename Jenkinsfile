pipeline {
environment {
    imagename = "ceyhunqarayev/phonebookfinal:latest"
    dockerImage = ''
  }
    agent any
    stages {
        stage ('Compiling App') {
            steps {
                withMaven(maven : 'maven_3') {
                    sh 'mvn clean install'
                }
            }
        }
        stage ('Unit Testing') {
            steps {
                withMaven(maven : 'maven_3') {
                    sh 'mvn test'
                }
            }
        }
//         stage ('SonarQube checking') {
//             steps {
//                 withMaven(maven : 'maven_3') {
//                     withSonarQubeEnv('SonarQube') {
//                         sh 'mvn clean verify sonar:sonar'
//                     }
//                 }
//                 waitForQualityGate abortPipeline: true
//             }
//         }
        stage('Building Docker') {
            steps {
                 script {
                    dockerImage = docker.build imagename
                 }
            }
        }
        stage('Pushing Docker') {
            steps {
                 script {
                     withCredentials([string(credentialsId: 'Docker-Hub-Password', variable: 'dockerhubpwd')]) {
                         sh 'docker login -u ceyhunqarayev -p dockerhubpwd'
                     }
                     dockerImage.push('latest')
                 }
            }
        }
        stage('Deploying on Kubernetes') {
            steps {
                script {
                   sh 'kubectl rollout restart deployment/backend-final -n backend-app-final'
                }
            }
        }
    }
}