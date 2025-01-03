pipeline {
    agent any
    
    environment {
        // Set JAVA_HOME to use the installed JDK for the build and SonarQube analysis
        JAVA_HOME = 'C:/Program Files/Java/jdk-17;C:/Program Files/Java/jdk-17/bin'
    }
    
    tools {
        // Use the Maven installation defined in Jenkins
        maven 'Maven Default'  
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'  // Adjust the build command based on your needs
            }
        }

        stage('SonarAnalysis') {
            environment {
                // Fetch SonarQube token from Jenkins credentials
                SONAR_TOKEN = credentials('sonar_token')  // Adjust with your SonarQube token credential ID
            }
            steps {
                // Only for Windows systems, using the 'bat' command
                bat """
                    set PATH=%JAVA_HOME%\\bin;%PATH%
                    mvn clean verify sonar:sonar ^
                        -Dsonar.projectKey=maven ^
                        -Dsonar.projectName=maven ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONAR_TOKEN%
                """
            }
        }
    }

    post {
        success {
            echo 'Build and SonarQube analysis completed successfully!'
        }
        failure {
            echo 'Build or SonarQube analysis failed!'
        }
    }
}
