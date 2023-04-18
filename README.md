# spring-oauth2-login-template
> This template implements GitHub and Google's social login using Spring

> Adding other social logins is easy.


## Configuration

### application.yaml
```yaml
spring:
  application:
    name: spring-oauth2-login-template

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: << DB Url >>
    username: << DB User >>
    password: << DB Password >>

  sql:
    init:
      schema-locations: classpath:schema.sql
      mode: always

  jpa:
    hibernate:
      ddl-auto: create

    properties:
      hibernate:
        show_sql: true
        format_sql: true

    open-in-view: false
    defer-datasource-initialization: true

  session:
    jdbc:
      initialize-schema: always
      schema: classpath:org/springframework/session/jdbc/schema-mysql.sql
      platform: mysql

  security:
    oauth2:
      client:
        registration:
          github:
            client-id: << github client id >>
            client-secret: << github client secret >>

          google:
            client-id: << github client id >>
            client-secret: << github client secret >>

```
