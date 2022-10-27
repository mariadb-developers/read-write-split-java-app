# Spring Boot Data JPA + MariaDB MaxScale demo

This example shows how to use MariaDB MaxScale as a database proxy to load balance writes to
primary servers and reads to replicas.

## Set up the database using Docker

Clone the following repository which contains Docker images to set up replication and MaxScale:

```
git clone https://github.com/alejandro-du/mariadb-docker-deployments.git
```

Build the images:

´´´
cd mariadb-docker-deployments
docker build --file single-node/Dockerfile --tag alejandrodu/mariadb-single-node .
docker build --file primary/Dockerfile --tag alejandrodu/mariadb-primary .
docker build --file replica/Dockerfile --tag alejandrodu/mariadb-replica .
docker build --file maxscale/Dockerfile --tag alejandrodu/mariadb-maxscale .
´´´

Alternatively, you can use the **build.sh** script if you are on Linux.

Run the containers:

´´´
docker compose up -d
´´´

## Run the web application

Build the Java web application using Maven:

´´´
mvn package
´´´

Run it:

´´´
java -jar target/webapp.jar
´´´

Access the application in your browser at http://localhost:8080.

