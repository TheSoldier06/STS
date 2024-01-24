FROM openjdk:11
EXPOSE 8081
ADD target/Student_management-0.0.1-SNAPSHOT.jar Student_management-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/Student_management-0.0.1-SNAPSHOT.jar" ]