pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'mvn install'
            }
        }
        stage("Release") {
            when { tag "v*" }
            steps {
                sh "mvn -B release:prepare"
                sh "mvn -B release:perform"
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}
