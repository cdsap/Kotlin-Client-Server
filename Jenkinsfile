pipeline {
    agent any 
    stages {
        stage('Build') {
          parallel { 
           stage('Build1') {
                 agent any
                 steps {
                    sh './gradlew assembleDebug' 
                 }
           }
           stage('Build2'){
                agent any
                steps {
                   sh './gradlew assembleDebug'
               }
           } 
        }
    }
  }
}
