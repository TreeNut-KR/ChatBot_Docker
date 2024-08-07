도커 컨테이너 및 이미지 모두 삭제 후 자동 재빌드

리눅스, 맥 : ./rebuild.sh
윈도우 : & ./rebuild.bat

FastAPI 작업영역 : fastapi/sources
프론트 작업영역 : nginx/frontpage-react
프론트 빌드파일 경로 : nginx/frontpage-react/build

### 버전 태그 푸시

워크플로가 실행되려면, 버전 태그를 푸시해야 합니다. 예를 들어, `v0.0.1`이라는 태그를 푸시하여 새 릴리스를 생성할 수 있습니다.

```bash
git tag v0.0.1 # v0.0.1를 실제 태그로 수정, 생성됨
git push origin v0.0.1 # v0.0.1를 실제 태그로 수정하여 입력하면 푸시됨
```

### 버전 태그 삭제
```bash
git tag -d v0.0.1 # v0.0.1를 로컬에서 태그 삭제
git push origin --delete v0.0.1 # v0.0.1를 원격 저장소에서 태그 삭제 
```
