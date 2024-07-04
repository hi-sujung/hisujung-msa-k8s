# 베이스 이미지로 Python 3.9 사용
FROM python:3.9-slim

# 작업 디렉토리 설정
WORKDIR /app

# 필요한 파일들을 컨테이너의 작업 디렉토리로 복사
COPY . /app

# 의존성 파일 설치
COPY requirements.txt /app/requirements.txt
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt

# 필요한 경우, 실행할 스크립트를 지정
CMD ["python", "univ_activity_crawler.py"]
