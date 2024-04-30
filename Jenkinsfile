pipeline {
    agent any

    tools {
        nodejs 'NodeJS 20.10.0' // Specify needed nodejs installation where npm installed packages will be provided to the PATH
    }
    stages {
        stage('Environment') {
            steps{
                echo 'Environement set..'
                git branch: 'main', url: 'https://github.com/movik21/DevOpsDemo.git'
                deleteDir()
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                // Build Step: Invoke Gradle script
                dir('backend') {
                    sh './gradlew Test'
                }

                // Build Step: Install and run NPM for frontend
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                    }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
        always {
            junit '**/test-results/test/*.xml'
            jacoco(
                execPattern: '**/**.exec', // Path to exec files (e.g.: **/target/**.exec, **/jacoco.exec)
                classPattern: '**/classes', // Path to class directories (e.g.: **/target/classDir, **/classes)
                sourcePattern: '**/src/main/java', // Path to source directories (e.g.: **/mySourceFiles)
            )
        }
        success {
            echo 'Build and tests succeeded.'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}