@echo off

rem Minikube 삭제
minikube delete

rem 모든 PVC와 PV 삭제
kubectl delete pvc --all
kubectl delete pv --all

rem 모든 Docker 컨테이너 중지 및 삭제
for /f "tokens=*" %%i in ('docker ps -aq') do (
    docker stop %%i
    docker rm %%i
)

rem 모든 Docker 이미지 삭제
for /f "tokens=*" %%i in ('docker images -q') do (
    docker rmi %%i
)

rem Docker Compose 빌드 및 푸시
docker compose build
docker compose push

rem Minikube 시작
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
