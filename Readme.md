# Todo Something

Simple Java Spring Boot application 
using Thymeleaf as a template engine for view creation.
Mustache as a template engine for HTML response
and [HTMX](https://htmx.org) for adding additional Hypertext behaviors to HTML tags.
You can also read [Hypermedia Systems](https://hypermedia.systems) for more information.  

## Local Execution
Local execution of the application expects
a redis service at the default host and port defined
in the application.properties file.

You can use docker compose to start a redis service from the compose file
scripts/docker-compose.yml

- [ ] Start the local container for Redis via docker-compose
```shell
docker-compose -f scripts/docker-compose.yml up
```

## Docker build
You can build the docker image using the following maven command
The version if NOT specified will default to 0.0.1-SNAPSHOT.
 ```shell
 mvn spring-boot:build-image -Dversion=1.1.0
 ```
- [ ] Build the docker container for execution

## Kubernetes Execution
The application was tested on a local [Orbstack](https://orbstack.com) Kubernetes cluster.

The kubernetes configuration requires that a Gateway controller be installed.
The Gateway controller is a custom controller that manages the creation of Gateway access into the cluster.
Orbstack allows for local routing of *.k8s.orb.local at port 80 or 443 into the cluster and 
can be used with host matching for routing to specific services.
[Gateway Ingress Controller Installation](./ingress-controller-install.md)

 - [ ] Install the Redis Service
    ```shell
   kubectl apply -f scripts/k8s/redis-service.yml
    ```
  - [ ] Install the application and the service
    ```shell
    kubectl apply -f scripts/k8s/todosomething.yml
    ```
  - [ ] Install the ingress for the service
    ```shell
    kubectl apply -f scripts/k8s/todosomething-ingress.yml
    ```  
    
## Modify the application to include a clear all button

