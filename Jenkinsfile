pipeline {
    agent any 
    stages {
        stage('Build') {
          parallel { 
           stage('Build1') {
                 steps {
                    sh './gradlew assembleDebug' 
                 }
           }
           stage('Build2'){
                steps {
                   sh './gradlew assembleDebug'
               }
           } 
        }
    }
  }
}
