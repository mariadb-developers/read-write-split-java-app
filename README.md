# Spring Boot Data JPA + MariaDB MaxScale demo

[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=plastic)](https://opensource.org/licenses/MIT)

This example shows how to use [MariaDB MaxScale](https://mariadb.com/docs/products/mariadb-maxscale/) as a database proxy to load balance writes to
primary servers and reads to replicas.

![MariaDB MaxScale database proxy](https://repository-images.githubusercontent.com/558545499/2696d4ed-f270-4ef5-9c97-7516e7ac6f2c)

## Set up the database using Docker

Clone the following repository which contains Docker files to set up MariaDB replication and MaxScale:

```
git clone https://github.com/alejandro-du/mariadb-docker-deployments.git
```

Build the images (don't use these in production environments):

```
cd mariadb-docker-deployments
docker build --file single-node/Dockerfile --tag alejandrodu/mariadb-single-node .
docker build --file primary/Dockerfile --tag alejandrodu/mariadb-primary .
docker build --file replica/Dockerfile --tag alejandrodu/mariadb-replica .
docker build --file maxscale/Dockerfile --tag alejandrodu/mariadb-maxscale .
```

Alternatively, you can use the **build.sh** script if you are on Linux.

Run the containers:

```
docker compose up -d
```

## Run the web application

Clone this repository:

```
git clone https://github.com/mariadb-developers/read-write-split-java-app.git
```

Build the Java web application using Maven:

```
cd read-write-split-java-app
mvn package
```

Run the application:

```
java -jar target/webapp.jar
```

Access the application in your browser at http://localhost:8080.

Access the MaxScale GUI at http://localhost:8989. Use `admin`/`mariadb` to log in.

To shutdown the database cluster run:

```
docker compose down
```

## Support and Contribution

Please feel free to submit PR's, issues or requests to this project
directly.

If you have any other questions, comments, or looking for more information
on MariaDB please check out:

* [MariaDB Developer Hub](https://mariadb.com/developers)
* [MariaDB Community Slack](https://r.mariadb.com/join-community-slack)

Or reach out to us directly via:

* [developers@mariadb.com](mailto:developers@mariadb.com)
* [MariaDB Twitter](https://twitter.com/mariadb)
