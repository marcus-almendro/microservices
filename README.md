# Microservices using Spring Cloud and DDD
Demo project using the Spring Cloud stack for microservices.
The domain has two contexts: Product Catalog and Order Management.
The former provides product prices to the latter. 
If the Product Catalog service is down, then the Order Management service fallbacks to a local cache.
### Components used:
* Eureka
* Config Server
* Zuul Gateway
* Sleuth
* Zipkin
* Feign
* Ribbon
* Hystrix
* EhCache
* MySql
* H2
* ModelMapper

### Services exposed:

| Service          | Address                                         |
|------------------|-------------------------------------------------|
| MySql            | localhost:3306                                  |
| Eureka           | http://localhost:8700                           |
| Config Server    | http://localhost:8000                           |
| Zuul Gateway     | http://localhost:8900                           |
| Zipkin           | http://localhost:9411                           |
| Product Catalog  | http://localhost:8001 and http://localhost:8002 |
| Order Management | http://localhost:8081                           |

### Steps to run:

1) Clone repository
2) run.sh (linux) or run.bat (windows)
