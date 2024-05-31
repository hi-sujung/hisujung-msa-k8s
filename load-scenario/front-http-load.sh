
# 부하 시나리오 1: msa-front 컨테이너에 1초마다 HTTP GET 요청을 보내기
# 실행 방법: 터미널에 chmod +x front-http-load.sh 입력 -> ./front-http-load.sh을 통해 부하 시나리오 1에 대한 쉘 스크립트 실행

while true; do
    curl http://front-service.default.svc.cluster.local
    sleep 1  
done
