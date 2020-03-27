pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'mvn -f jparestresource/pom.xml install'
            }
        }
        stage("Release") {
            when { tag "v*" }
            steps {
                sh "mvn -f jparestresource/pom.xml -B release:prepare"
                sh "mvn -f jparestresource/pom.xml -B release:perform"
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}
