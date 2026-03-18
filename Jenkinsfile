pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "shopping-cart-app"
    }

    stages {

        stage('Checkout') {
            steps {
                // Get code from Git (replace with your repo)
                git 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Build JAR') {
            steps {
                // Use Maven to build the project
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Container') {
            steps {
                // Optional: run container (for testing)
                sh "docker run -d --name ${DOCKER_IMAGE} -p 8080:8080 ${DOCKER_IMAGE}"
            }
        }

        stage('Clean Up') {
            steps {
                // Optional: stop & remove container
                sh "docker rm -f ${DOCKER_IMAGE} || true"
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}