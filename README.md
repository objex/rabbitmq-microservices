## Event Driven Microservices using RabbitMQ on Kubernetes Demo

## install rabbitmq on kubernetes (specs under ./k8)
kubectl apply -f rabbitmq.yaml

## build image
mvn spring-boot:build-image

### remote
docker tag producer:b0 objex/producer

docker tag subscriber:b0 objex/consumer

## push image to local registry
### local
docker push 192.168.64.2:32000/producer:b0

docker push 192.168.64.2:32000/consumer:b0

### remote
docker push objex/producer

docker push objex/consumer

## deploy to k8
kubectl create deployment producer --image=objex/producer

kubectl create deployment consumer --image=objex/consumer

## test
kubectl expose deployment producer --type NodePort --port 8889 --target-port 7777

kubectl expose deployment consumer --type NodePort --port 8899 --target-port 7777

### find port of the service and see consumed events
kubectl get svc producer

send message: http://192.168.64.2:31887/m/hi

kubectl get pods

kubectl logs <consumer-pod>