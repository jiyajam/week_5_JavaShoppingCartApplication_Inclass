FROM  maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="jiya"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/week_5_JavaShoppingCartApplication_Inclass-1.0-SNAPSHOT.jar"]