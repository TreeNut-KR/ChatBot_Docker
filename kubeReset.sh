#!/bin/bash

minikube delete
kubectl delete pvc --all
kubectl delete pv --all
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi $(docker images -q)
#eval $(minikube docker-env)
docker compose build
docker compose push
minikube start

echo "k8s 디렉토리에 작성된 Kubernetes 매니패스트 파일을 Minikube에 배포합니다."
kubectl apply -f k8s/secret.yaml
kubectl apply -f k8s/fastapi.yaml
kubectl apply -f k8s/mysql.yaml
kubectl apply -f k8s/nginx.yaml
kubectl apply -f k8s/pv.yaml
kubectl apply -f k8s/pvc.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/springboot.yaml
echo "배포가 끝났습니다. Minikube 대시보드를 실행합니다."
minikube dashboard