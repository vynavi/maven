pipeline {
    agent any
    
    environment {
        // Set JAVA_HOME to use the installed JDK for the build and SonarQube analysis
        JAVA_HOME = tool name: 'JDK 11', type: 'JDK'  
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
                bat '''
                set PATH=%JAVA_HOME%;%PATH%
                mvn clean verify sonar:sonar \ ^
  -Dsonar.projectKey=maven \ ^
  -Dsonar.projectName='maven' \ ^
  -Dsonar.host.url=http://localhost:9000 \ ^
  -Dsonar.token=sqp_48085db02c22f4ee8dea86eb85a20098149a4b27
                '''
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
