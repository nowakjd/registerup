pipeline {
    agent any

    options {
        buildDiscarder logRotator(numToKeepStr: '15')
        disableConcurrentBuilds()
        timestamps()
    }

    stages {
        stage('build') {
            agent {
                docker {
                    image 'openjdk:17'
                }
            }
            steps {
                timeout(3) {
                    sh './gradlew clean build'
                }
                junit 'build/test-results/test/*.xml'
                jacoco()
                stash includes: 'build/libs/*.jar', name: 'jars'
            }
        }
        stage('build_image') {
            agent any
            steps {
                unstash 'jars'
                // TODO build docker image
            }
        }
    }
}
