pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = "jiyajameela"            // Docker Hub username
        DOCKERHUB_CREDENTIALS = "jiyak"           // Jenkins credential ID
        IMAGE_NAME = "shopping-cart-app"
        BUILD_TAG = "${env.BUILD_NUMBER}"         // Unique tag per Jenkins build
        DOCKER_IMAGE = "${DOCKER_HUB_USER}/${IMAGE_NAME}:${BUILD_TAG}"
        CONTAINER_NAME = "${IMAGE_NAME}-${BUILD_TAG}" // Unique container name
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
                // Stop & remove old container if exists
                sh "docker rm -f ${CONTAINER_NAME} || true"

                // Run new container
                sh "docker run -d --name ${CONTAINER_NAME} -p 8080:8080 ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        always {
            echo "Pipeline finished for build #${BUILD_NUMBER}"
        }
        failure {
            echo "Build #${BUILD_NUMBER} failed!"
        }
    }
}