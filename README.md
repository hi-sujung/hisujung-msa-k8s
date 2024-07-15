# MSA와 k8s를 통한 하이수정 고도화 프로젝트
about hisujung-msa-k8s

## Architecture Diagram
<img width="619" alt="스크린샷 2024-06-18 오후 9 54 33" src="https://github.com/hi-sujung/hisujung-msa-k8s/assets/100345983/ff795ff0-68f7-4a73-bb42-f09593655db4">

## Folder Structure
```
.
├── cloudflare
│   ├── notification
│   │     └── slack-gke.js
│   └── web
│         └── address.md
├── k8s-manifest
│   ├── hisujung-all.yaml
│   ├── hisujung-configmap.yaml
│   ├── hisujung-ingress.yaml
│   └── hisujung-secret.yaml
├── load-test
│   ├── hisujung-configmap.yaml
│   ├── hisujung-hpa.yaml
│   ├── hisujung-ingress.yaml
│   ├── hisujung-secret.yaml
│   └── hisujung-test.yaml
├── microservices
│   ├── auth-jwt
│   ├── crawling-external
│   ├── crawling-univ
│   ├── front-web
│   ├── member
│   ├── notice
│   ├── portfolio
│   ├── rabbitmq
│   └── recommend
├── scripts
│   └── k8s-script.sh
├── .gitignore
└── README.md
```

## About microservices
| 이름                 | 기능                                                | k8s 오브젝트                               |
|---------------------|---------------------------------------------------|------------------------------------------|
| [auth-jwt](https://github.com/hi-sujung/msa-auth-jwt) | JWT 검증 및 사용자 정보 전달                                | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/auth-jwt/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/auth-jwt/service.yaml) |
| [crawling-external](https://github.com/hi-sujung/msa-crawling-external) |                                                   | [CronJob](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/crawling-external/cronjob.yaml) |
| [crawling-univ](https://github.com/hi-sujung/msa-crawling-univ) |                                                   | [CronJob](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/crawling-univ/cronjob.yaml) |
| [front-web ](https://github.com/hi-sujung/msa-front-web) |                                                   | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/front-web/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/front-web/service.yaml) |
| [member](https://github.com/hi-sujung/msa-member) | 회원가입(이메일 인증), 로그인, 로그아웃                           | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/service.yaml) |
| [notice](https://github.com/hi-sujung/msa-notice) | 교내공지 및 대외활동 저장,조회,좋아요,참여 여부 관리, 추천 교내공지 및 대외활동 조회 | [ConfigMap](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/configmap.yaml), [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/service.yaml) |
| [portfolio](https://github.com/hi-sujung/msa-portfolio) |                                                   | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/service.yaml) |
| [rabbitmq](https://github.com/hi-sujung/msa-rabbitmq) |                                                   | [PVC](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/pvc.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/service.yaml), [StatefulSet](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/statefulset.yaml) |
| [recommend](https://github.com/hi-sujung/msa-recommend) |                                                   | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/recommend/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/recommend/service.yaml) |


## How to start
1.


## How to test
1. 


