FROM openjdk:8

ARG HOST
ARG PORT
ENV host=$HOST
ENV port=$PORT
ADD target/SchemaDB-1.0-SNAPSHOT.jar ./SchemaDB-1.0-SNAPSHOT.jar

EXPOSE $PORT

CMD ["sh","-c","java -jar -Ddatabase=${host} -Dserver.port=${port} SchemaDB-1.0-SNAPSHOT.jar"]