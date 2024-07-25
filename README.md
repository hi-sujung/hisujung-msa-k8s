# MSA와 k8s를 통한 하이수정 고도화 프로젝트

## Architecture Diagram
<img width="700" alt="스크린샷 2024-06-18 오후 9 54 33" src="https://github.com/user-attachments/assets/c4f2ce7a-1b52-4773-b5b2-c5083d372e09">

## About microservices
| 이름                 | 기능                                          | k8s 오브젝트                               |
|---------------------|---------------------------------------------|------------------------------------------|
| [auth-jwt](https://github.com/hi-sujung/msa-auth-jwt) | JWT 검증 및 사용자 정보 전달 | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/auth-jwt/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/auth-jwt/service.yaml) |
| [crawling-external](https://github.com/hi-sujung/msa-crawling-external) | 대외 활동 데이터 크롤링 | [CronJob](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/crawling-external/cronjob.yaml) |
| [crawling-univ](https://github.com/hi-sujung/msa-crawling-univ) | 교내 활동 데이터 크롤링 | [CronJob](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/crawling-univ/cronjob.yaml) |
| [front-web ](https://github.com/hi-sujung/msa-front-web) | 웹 UI 제공, ingress를 통해 서버와 통신, 회원 로그아웃 | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/front-web/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/front-web/service.yaml) |
| [member](https://github.com/hi-sujung/msa-member) | 회원가입, 로그인 | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/member/service.yaml) |
| [notice](https://github.com/hi-sujung/msa-notice) | 교내공지 및 대외활동 저장, 조회, 좋아요, 참여 여부 관리<br/> 추천 교내공지 및 대외활동 조회 | [ConfigMap](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/configmap.yaml), [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/notice/service.yaml) |
| [portfolio](https://github.com/hi-sujung/msa-portfolio) | 포트폴리오 CRUD, 포트폴리오 자동생성 | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/deployment.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/portfolio/service.yaml) |
| [rabbitmq](https://github.com/hi-sujung/msa-rabbitmq) | crawling MS(univ, external)와 notice MS 간 메세지 브로커 | [PVC](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/pvc.yaml), [Secret](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/secret.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/service.yaml), [StatefulSet](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/rabbitmq/statefulset.yaml) |
| [recommend](https://github.com/hi-sujung/msa-recommend) | 사용자 맞춤 공지사항 추천 | [Deployment](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/recommend/deployment.yaml), [Service](https://github.com/hi-sujung/hisujung-msa-k8s/blob/main/k8s-manifest/recommend/service.yaml) |

