
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

docker service create --name springboot2cassandra --replicas 2 -p 8080:8080 skadthan/springboot2cassandra:v1