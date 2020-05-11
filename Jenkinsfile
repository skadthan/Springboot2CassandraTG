
pipeline {

	agent any

	
	stages {

		stage('Get code from GitHub') { 
		
		    steps {
      		// Get code from a GitHub repository
      		   echo 'Get code from a GitHub repository'
      		   git 'https://github.com/skadthan/Springboot2CassandraTG.git'
  				 }
  		     }
  		     
  		 stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
  		
 
		stage('Build and Unit Test') {
      		// Run build and test
      		 steps {
      			 withEnv(["JAVA_HOME=${ tool 'jdk-1.8.0_64bits' }", "PATH+MAVEN=${tool 'maven-3.2.1'}/bin:${env.JAVA_HOME}/bin"]) {
      		 
      			 echo 'Run build and test'
      			 sh 'mvn -Dmaven.test.failure.ignore=true install'
      			  }
   				}
   			 post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
   			}
		stage('SonarQube analysis') { 
			 
			 steps {
			 echo 'Sonarqube Analysis'
       		 withSonarQubeEnv('Sonar') { 
      		 sh '''cd $WORKSPACE/Springboot2CassandraTG && ./mvnw sonar:sonar \
			 -Dsonar.host.url=http://localhost:9000 \
			 -Dsonar.login=admin \
			 -Dsonar.password=admin\
			 -Dsonar.sources=src/main \
			 -Dsonar.java.binaries=build/classes \
			 -Dsonar.java.libraries=build/libs/*.jar
			 ........'''
        		}
        	}
  		 }

		stage('Publish Images to Hub') {
			steps {
			
   				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '5e1c35ab-1404-4165-b224-8894cc70', usernameVariable: 'skadthan', passwordVariable: 'Ashu#123'],]) {
        		sh '''docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
    			cd $WORKSPACE/Springboot2CassandraTG && ./docker image build -t skadthan/springboot2cassandra:v1 . '''
    			}
    		}
   		}

		stage('Deploy Images with Docker-Compose') {
			 steps {
      			 sh ''' ./docker container run --name springboot2cassandra -p 8090:8090 -d skadthan/springboot2cassandra:v1 '''
  			 }
		}
		
		stage('HealthCheck') {
			steps {
      			echo 'Checking Health of the Application'
     			// httpRequest responseHandle: 'NONE', url: 'http://dev.eodessa.com/health'
   				}
   		}
   
		stage('Integration Test') {
			steps {
 	  		   echo 'Performing the Integration Test'
      			//sh '''cd $WORKSPACE/test-integration && ./gradlew clean test'''
   				}
 		}
 
 		stage('UI Tests') {
 			steps {
        		echo 'Performing the UI automated tests'
      			//sh '''cd $WORKSPACE/test-integration && ./gradlew clean test'''
  				 }
  			} 
   	}
 }