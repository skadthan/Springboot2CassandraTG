// Define Release Number
def prev_release = "V6"
def new_release="V7"

pipeline {

	agent any
	tools { 
          maven '3.8.4' 
          jdk 'OpenJDK12' 
      }
	
	 /*agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /Users/skadthan/.m2:/root/.m2' 
        }
    }*/
    
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
            	sh "whoami"
                sh "echo PATH = ${PATH}"
                sh "echo M2_HOME= ${M2_HOME}"
                sh "/usr/local/bin/docker -v"
                sh "docker -v"
                
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
       		 
       		  	sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=abcd123'
        		}
  		 	}

		stage('Build & Publish Images to Hub') {
			steps {
   				script {
       				// docker.build registry + ":${release}"
       				sh "docker image build -t skadthan/springboot2cassandra:${new_release} ."
       		
       				 sh "docker push skadthan/springboot2cassandra:${new_release}"
      				}
      				
      				// withDockerRegistry([ credentialsId: "dockerhub", url: "https://hub.docker.com" ]) {
          			//	sh "/usr/local/bin/docker push skadthan/springboot2cassandra:${release}"
        			//}
    			}
   		}

		stage('Deploy Images with Docker-Compose') {
			 steps {
      			echo 'deploy the containers'
			sh "docker stop springboot2cassandra${prev_release} || true && docker rm springboot2cassandrag${prev_release} || true"
      			sh "docker container run  --name springboot2cassandra${new_release} -p 8090:8090 -d skadthan/springboot2cassandra:${new_release}"
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
