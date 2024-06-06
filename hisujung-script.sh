echo "Applying secrets and configmaps..."

# 시크릿
echo "Deleting and applying hisujung-secret.yaml..."
kubectl delete -f "hisujung-secret.yaml"
kubectl apply -f "hisujung-secret.yaml"

# 컨피그맵
echo "Deleting and applying hisujung-configmap.yaml..."
kubectl delete -f "hisujung-configmap.yaml"
kubectl apply -f "hisujung-configmap.yaml"


# 데이터베이스와 관련된 설정
dbms=("k8s-mysql" "k8s-redis" "k8s-redis-ha")

# 각 데이터베이스 설정에 대해 반복하여 리소스 삭제 및 적용
for dbmsname in "${dbms[@]}"
do
    echo "Deleting and applying ${dbmsname}.yaml..."
    kubectl delete -f "${dbmsname}.yaml"
    kubectl apply -f "${dbmsname}.yaml"
done


# RabbitMQ와 관련된 설정
echo "Deleting and applying k8s-rabbitmq.yaml..."
kubectl delete -f "k8s-rabbitmq.yaml"
kubectl apply -f "k8s-rabbitmq.yaml"


# 마이크로서비스 설정
ms=("msa-crawling" "msa-front" "msa-member" "msa-notice" "msa-portfolio" "msa-recommend" "msa-spring-cloud-gateway")

# 각 마이크로서비스에 대해 반복하여 리소스 삭제 및 적용
for msname in "${ms[@]}"
do
    echo "Deleting and applying ${msname}.yaml..."
    kubectl delete -f "${msname}.yaml"
    kubectl apply -f "${msname}.yaml"
done

echo "All resources have been applied successfully."
