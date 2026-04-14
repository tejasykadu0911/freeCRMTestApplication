pipeline {
    agent any

    tools {
        maven 'Maven'        // Name set in Jenkins Global Tools
        allure 'allure'      // Name set in Jenkins Global Tools
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                        url: 'https://github.com/tejasykadu0911/freeCRMTestApplication.git',
                        credentialsId: 'tejasykadu0911'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }

    post {
        always {
            allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
            ])
        }
    }
}