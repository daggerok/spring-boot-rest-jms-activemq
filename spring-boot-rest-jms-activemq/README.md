# spring-boot jms activemq [![Build Status](https://travis-ci.org/daggerok/spring-boot-rest-jms-activemq.svg?branch=master)](https://github.com/daggerok/spring-boot-rest-jms-activemq)

This repository contains some activemq examples

**gradle wrapper**

```bash
gradle wrapper --gradle-version=4.2.1 --distribution-type=all
bash gradlew build bootRun
curl localhost:8080/send/Hello,%20World!
```

**maven wrapper**

```bash
mvn -N io.takari:maven:wrapper
bash mvnw clean package
bash mvnw spring-boot:run
```

**get**

```bash
git clone https://github.com/daggerok/spring-boot-rest-jms-activemq.git
cd spring-boot-rest-jms-activemq
```
