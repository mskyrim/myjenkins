# create jenkins network
docker network create jenkins

docker build -t myjenkins-blueocean:1.1 .

# run jenkins container
  sudo docker run \                                                                                                                                                                                   ✔ 
  --name jenkins-blueocean \
  --rm \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8087:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:1.1


# jenkins link
http://localhost:8087
