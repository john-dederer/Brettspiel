pipeline {
  agent any
  stages {
    stage('') {
      steps {
        parallel(
          "Stage 1": {
            echo 'Hello'
            echo 'Whatup'
            
          },
          "Stage 2": {
            echo 'Other Stage'
            
          }
        )
      }
    }
  }
}