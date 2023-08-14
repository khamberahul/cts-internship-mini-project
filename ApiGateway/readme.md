# Eureka client

* Spring boot with web starter, actuator starter and eureka discovery starter
* Use `@EnableEurekaClient` annotation to enable service as a client. 
  * With above annotation when start the application it would be registered with the Eureka service registry 
    as an _UNKNOWN_ application.
  * By default will search the Eureka server at port _8761_ in the local machine
  * If application is stopped then Eureka server (management api) will show the state of the app as _DOWN_
  * If application is down for a longer period then Eureka server will eliminate from the registry
* Additional configurations for the client are made in the `application.[properties|yml]`
  * `spring.application.name=[NAME]` it is important to identify the microservice and is used in other pieces of spring cloud infrastructure
  * `eureka.client.healthcheck.enabled=true` it is important to have actuator starter as dependency to avoid `NoClassDefFoundError: org/springframework/boot/actuate/health/HealthIndicato...`

* [second eureka client service](https://github.com/maurofokker/spring-microservices-eureka-client)

## Service discovery using `RestTemplate`
  * add property `eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/` for service discovery
  * add `RestTemplate` bean and apply for that bean the annotation `@LoadBalanced`
  * to discover with rest template
    ```java
      return this.restTemplate.getForObject("http://MYSECONDCLIENT/serviceinfo", String.class);
    ```
    * use app name registered in eureka server (spring app name) capitalized

## Troubleshootings

* [fixing eureka application status](https://jmnarloch.wordpress.com/2015/09/02/spring-cloud-fixing-eureka-application-status/)
