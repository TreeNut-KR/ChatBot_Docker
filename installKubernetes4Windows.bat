@echo off
echo Minikube 및 kubectl 설치 시작합니다.
echo.
echo Hyper-V 활성화 방법
echo 1. 제어판을 열고, 프로그램 > 프로그램 및 기능 > Windows 기능 켜기/끄기로 이동합니다.
echo 2. Hyper-V를 선택하고 확인합니다.
echo 3. 컴퓨터를 재부팅합니다.
pause

:: Chocolatey 설치
echo Chocolatey 설치 중...
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))"

:: Minikube 설치
echo Minikube 설치 중...
choco install minikube -y

:: kubectl 설치
echo kubectl 설치 중...
choco install kubernetes-cli -y

echo 설치가 완료되었습니다.
pause
