
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
      			 sh 'mvn clean test'
      			 sh ''' cd $WORKSPACE/Springboot2CassandraTG && ./mvnw sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin '''
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
       		 
       		 withSonarQubeEnv('sonarqube') {
           				 sh "${scannerHome}/bin/sonar-scanner"
        			}
       		 timeout(time: 10, unit: 'MINUTES') {
            			waitForQualityGate abortPipeline: true
        			}
        		}
  		 	}

		stage('Publish Images to Hub') {
			steps {
   				script {
       				 docker.build registry + ":$BUILD_NUMBER"
      				}
    			}
   		}

		stage('Deploy Images with Docker-Compose') {
			 steps {
      			echo 'deploy the containers'
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