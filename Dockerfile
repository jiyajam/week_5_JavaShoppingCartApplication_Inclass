# Use a supported Temurin Java image
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy your JAR
COPY target/week_5_JavaShoppingCartApplication_Inclass-1.0-SNAPSHOT.jar app.jar

# Run the JAR
CMD ["java", "-jar", "app.jar"]