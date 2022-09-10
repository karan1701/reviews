FROM openjdk:8
EXPOSE 9093
ADD target/reviews.jar reviews.jar
ENTRYPOINT ["java", "-jar", "/reviews.jar"]