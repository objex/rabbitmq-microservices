docker rmi objex/producer
docker rmi objex/consumer
mvn spring-boot:build-image
docker tag producer:b0 objex/producer
docker tag consumer:b0 objex/consumer
docker push objex/producer
docker push objex/consumer
kubectl delete deployment producer
kubectl create deployment producer --image=objex/producer
kubectl delete deployment consumer
kubectl create deployment consumer --image=objex/consumer
