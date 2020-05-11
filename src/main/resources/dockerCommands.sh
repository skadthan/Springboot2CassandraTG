
docker login
docker image build -t skadthan/springboot2cassandra:v1 .
docker container run --name springboot2cassandra -p 8080:8080 -d skadthan/springboot2cassandra:v1
docker push skadthan/springboot2cassandra:v1
docker logs springboot2cassandra
docker exec -it --user root springboot2cassandra /bin/sh
apk update
apk add iputils
apk add busybox-extras

#Cassandra
docker network create -d bridge cassandra-net
docker run --name ashu-cassandra -p 9042:9042 -p 9160:9160 --network cassandra-net -d cassandra
docker exec -it springboot2cassandra bash
apt-get update
apt-get install vim

#Docker Swarm
docker swarm init
docker node ls

#To add a worker to this swarm, run the following command:
#docker swarm join --token SWMTKN-1-5541joibfa4cqy1lb3i2m6emhcc76hvrzwjecqm8ikdsufw3ge-51l6y92s7rmackbfezu7dtl2d 192.168.65.3:2377
#To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions

#create microservice containers in docker swarm
docker service create --name springboot2cassandra --replicas 2 -p 8080:8080 skadthan/springboot2cassandra:v1

#SonarQube
mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
docker pull sonarqube
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube

#Jenkins
sudo docker run -u root --rm -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkinsci/blueocean