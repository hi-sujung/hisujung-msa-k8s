import os
import re
import pika
import requests
import json
from bs4 import BeautifulSoup

# 서비스계정 key JSON 파일 경로 설정
os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = "./rising-woods-388317-8a18f2625ba5.json"

def crawl_and_publish_to_rabbitmq():
    url = 'https://www.campuspick.com/activity/view?id=25379'

    HEADERS = {'User-Agent': 'Mozilla/5.0 (iPad; CPU OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148'}
    html = requests.get(url, headers=HEADERS).text

    content = re.search(r'cp\.activityview\.data = {(.*?)};', html, re.S).group(1)

    # Use regular expressions to capture specific fields
    title = re.search(r'title: "(.*?)"', content).group(1)
    startDate = re.search(r'startDate: "(.*?)"', content).group(1)
    endDate = re.search(r'endDate: "(.*?)"', content).group(1)
    website = re.search(r'website: "(.*?)"', content).group(1)
    company = re.search(r'company: "(.*?)"', content).group(1)
    description = re.search(r'description: "(.*?)"', content).group(1)

    # RabbitMQ 연결 설정
    credentials = pika.PlainCredentials(os.getenv('RABBITMQ_USER'), os.getenv('RABBITMQ_PASSWORD'))
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=os.getenv('RABBITMQ_HOST'), port=5672, credentials=credentials)) 
    channel = connection.channel()
    # 큐를 durable=True로 선언
    channel.queue_declare(queue='external_act_queue', durable=True)

    # 데이터를 RabbitMQ에 publish
    message = {
        'title': title,
        'category': '이벤트',
        'startingDate': startDate,
        'deadline': endDate,
        'host': company,
        'content': description,
        'link': website
    }
    channel.basic_publish(
        exchange='', 
        routing_key='external_act_queue', 
        body=json.dumps(message, ensure_ascii=False)
    )
    print(" [x] Sent %r" % message)

    # RabbitMQ 연결 닫기
    connection.close()

# 스크립트 파일을 직접 실행할 때 crawl_and_publish_to_rabbitmq() 함수 호출
if __name__ == "__main__":
    crawl_and_publish_to_rabbitmq()
