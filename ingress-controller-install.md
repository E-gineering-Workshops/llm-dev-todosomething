# Nginx Ingress Installation

## Helm Install
Tested with [version 3.6](https://hub.docker.com/r/nginx/nginx-ingress/tags)

Install the Ingress controller and CRDs directly using the
default values and the yaml.
[Instructions](https://docs.nginx.com/nginx-ingress-controller/installation/installing-nic/installation-with-manifests/)

## Get the CRDs to install ahead of the Helm chart installation
[Install CRD](https://docs.nginx.com/nginx-ingress-controller/installation/installing-nic/installation-with-manifests/#create-custom-resources)

```bash
# clone the repo
git clone https://github.com/nginxinc/kubernetes-ingress.git --branch v3.6.2
# move to the branch clone of the repo
# setup namespace
kubectl apply -f deployments/common/ns-and-sa.yaml
# setup RBAC
kubectl apply -f deployments/rbac/rbac.yaml
# setup configmap for customization
kubectl apply -f deployments/common/nginx-config.yaml
# create an ingress class
kubectl apply -f deployments/common/ingress-class.yaml
# create all crds
kubectl apply -f config/crd/bases/k8s.nginx.org_virtualservers.yaml
kubectl apply -f config/crd/bases/k8s.nginx.org_virtualserverroutes.yaml
kubectl apply -f config/crd/bases/k8s.nginx.org_transportservers.yaml
kubectl apply -f config/crd/bases/k8s.nginx.org_policies.yaml
kubectl apply -f config/crd/bases/k8s.nginx.org_globalconfigurations.yaml

```

## Install the controller as a Daemonset
```bash
kubectl apply -f deployments/daemon-set/nginx-ingress.yaml
```
The default configuration listens on port 80 and 443 for routing