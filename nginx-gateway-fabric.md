# Install of nginx gateway fabric
[github repo](https://github.com/nginxinc/nginx-gateway-fabric?tab=readme-ov-file)

## Install as manifests
From the [instructions](https://docs.nginx.com/nginx-gateway-fabric/installation/installing-ngf/manifests/)

1. Install gateway resources
 ```shell
  kubectl kustomize "https://github.com/nginxinc/nginx-gateway-fabric/config/crd/gateway-api/standard?ref=v1.4.0" > nginx-gateway-fabric.yml
  
  # output copied to scripts/k8s/nginx-gateway-fabric
  kubectl apply -f scripts/k8s/nginx-gateway-fabric
  
 ```
2. Install custom CRDs
    ```shell
   wget https://raw.githubusercontent.com/nginxinc/nginx-gateway-fabric/v1.4.0/deploy/crds.yaml
   # output copied to scripts/k8s/nginx-gateway-fabric.yml
    kubectl apply -f scripts/k8s/nginx-gateway-fabric.yml
    ```
3. Install the NGINX Gateway Fabric
    ```shell
   wget https://raw.githubusercontent.com/nginxinc/nginx-gateway-fabric/v1.4.0/deploy/default/deploy.yaml
    ```