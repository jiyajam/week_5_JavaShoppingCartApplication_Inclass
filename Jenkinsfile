pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "jiyajameela/shopping-cart-app:1.0" // Docker Hub image name
        DOCKERHUB_CREDENTIALS = "jiyak"         // Jenkins credential ID
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/jiyajam/week_5_JavaShoppingCartApplication_Inclass'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS}",
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${DOCKER_IMAGE}"
            }
        }

        stage('Run Container for Testing') {
            steps {
                // Stop and remove old container if exists
                sh "docker rm -f shopping-cart-app || true"

                // Run container
                sh "docker run -d --name shopping-cart-app -p 8080:8080 ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
        failure {
            echo 'Build failed!'
        }
    }
}