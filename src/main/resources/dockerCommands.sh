
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