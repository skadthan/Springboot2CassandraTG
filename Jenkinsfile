node {

stage('Get code from GitHub') { 
      // Get code from a GitHub repository
       echo 'Get code from a GitHub repository'
      git 'https://github.com/skadthan/Springboot2CassandraTG.git'
   }
   
stage('Build and Unit Test') {
      // Run build and test
      echo 'Run build and test'
      sh '''cd $WORKSPACE/Springboot2CassandraTG && ./mvnw clean test'''
   }
   
stage('SonarQube analysis') { 
        withSonarQubeEnv('Sonar') { 
      
      echo 'Sonarqube Analysis'  
      sh '''cd $WORKSPACE/Springboot2CassandraTG && ./mvnw sonar:sonar \
 -Dsonar.host.url=http://localhost:9000 \
 -Dsonar.login=admin \
 -Dsonar.password=admin\ \
 -Dsonar.sources=src/main \
 -Dsonar.java.binaries=build/classes \
 -Dsonar.java.libraries=build/libs/*.jar
 ........'''
        }
   }

   
stage("SonarQube Quality Gate") { 
        timeout(time: 1, unit: 'HOURS') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
        }
   }
   
stage('Publish Images to Hub') {
   		withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '5e1c35ab-1404-4165-b224-8894cc70', usernameVariable: 'skadthan', passwordVariable: 'Ashu#123'],]) {
        sh '''docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
    	cd $WORKSPACE/Springboot2CassandraTG && ./docker image build -t skadthan/springboot2cassandra:v1 . '''
    	}
   }

stage('Deploy Images with Docker-Compose') {
       sh ''' ./docker container run --name springboot2cassandra -p 8090:8090 -d skadthan/springboot2cassandra:v1 '''
   }
   
stage('HealthCheck') {
      echo 'Checking Health of the Application'
     // httpRequest responseHandle: 'NONE', url: 'http://dev.eodessa.com/health'
   }
   
stage('Integration Test') {

 	   echo 'Performing the Integration Test'
      //sh '''cd $WORKSPACE/test-integration && ./gradlew clean test'''
   }
 }
 
 stage('UI Tests') {
        echo 'Performing the UI automated tests'
      //sh '''cd $WORKSPACE/test-integration && ./gradlew clean test'''
   }
 }