mvn clean compile package

docker build  --build-arg HOST=redis \
              --build-arg PORT=8080  \
              -t schemadb1:latest .

docker build --build-arg HOST=mongo \
              --build-arg PORT=8081 \
              -t schemadb2:latest .

docker-compose up -d

docker ps