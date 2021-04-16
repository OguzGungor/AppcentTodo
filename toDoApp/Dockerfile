FROM java:8

ADD target/toDoApp-0.0.1-SNAPSHOT.jar toDoApp-0.0.1-SNAPSHOT.jar

COPY command.sh command.sh

ENTRYPOINT ["sh","command.sh"]

#ENTRYPOINT ["java" , "-jar" , "toDoApp-0.0.1-SNAPSHOT.jar"]