FROM openjdk:17-alpine
ADD target/expense-0.0.1-SNAPSHOT.jar expense-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/expense-0.0.1-SNAPSHOT.jar"]
