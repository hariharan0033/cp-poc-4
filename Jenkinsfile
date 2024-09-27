pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                script {
                    // Build commands
                    sh 'echo Building...'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    // SonarQube analysis
                    withSonarQubeEnv('SonarQube Server') {
                        sh 'sonar-scanner'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Deployment commands
                    sh 'echo Deploying...'
                }
            }
        }
    }
}
