FROM openjdk:17
ADD build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]
