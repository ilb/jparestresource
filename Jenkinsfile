pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'mvn -f jparestresource/pom.xml install'
            }
        }
        stage("Release") {
            when { expression { sh([returnStdout: true, script: 'echo $TAG_NAME | tr -d \'\n\'']) } }
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
