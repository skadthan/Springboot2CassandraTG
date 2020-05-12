
pipeline {

	agent any
	tools { 
        maven 'Maven 3.6.2' 
        jdk 'JDK12'
    }
    
	environment {
    registry = "skadthan/springboot2cassandra"
    registryCredential = 'dockerhub'
	}
	
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
            	sh 'whoami'
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
  		
 
		stage('Build and Unit Test') {
      		// Run build and test
      		 steps {
      			
      			 echo 'Run build and test'
      			 sh 'mvn clean test install'
      			
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
       		 
       		  	sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin'
        		}
  		 	}

		stage('Build & Publish Images to Hub') {
			steps {
   				script {
       				// docker.build registry + ":v1"
       				sh "/usr/local/bin/docker image build -t skadthan/springboot2cassandra:v1 ."
       		
       				// sh "/usr/local/bin/docker push skadthan/springboot2cassandra:v1"
      				}
      				
      				 withDockerRegistry([ credentialsId: "094d9620-4b88-422e-812e-021167c2b9cf", url: "" ]) {
          				sh "/usr/local/bin/docker push skadthan/springboot2cassandra:v1"
        }
    			}
   		}

		stage('Deploy Images with Docker-Compose') {
			 steps {
      			echo 'deploy the containers'
      			sh "/usr/local/bin/docker container run --name springboot2cassandra -p 8090:8090 -d skadthan/springboot2cassandra:v1"
  			 }
		}
		
		stage('HealthCheck') {
			steps {
      			echo 'Checking Health of the Application'
     			
   				}
   		}
   
		stage('Integration Test') {
			steps {
 	  		   echo 'Performing the Integration Test'
      			
   				}
 		}
 
 		stage('UI Tests') {
 			steps {
        		echo 'Performing the UI automated tests'
      			
  				 }
  			} 
   		}
   }