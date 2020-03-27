pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'mvn -f jparestresource/pom.xml install'
            }
        }
        stage("Release") {
            when { 
                expression {
                    sh(returnStdout: true, script: "git tag --contains | head -1").trim()
                }
            }
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
