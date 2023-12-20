# Spring Boot Data JPA + MariaDB MaxScale demo

[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=plastic)](https://opensource.org/licenses/MIT)

This example shows how to use [MariaDB MaxScale](https://mariadb.com/docs/products/mariadb-maxscale/) as a database proxy to load balance writes to
primary servers and reads to replicas.

![MariaDB MaxScale database proxy](https://repository-images.githubusercontent.com/558545499/2696d4ed-f270-4ef5-9c97-7516e7ac6f2c)

## Set up the database cluster using Docker

Clone this repository:

```shell
git clone https://github.com/mariadb-developers/read-write-split-java-app.git
```

The Docker image used in this demo comes from [this GitHub repository](https://github.com/alejandro-du/mariadb-docker-deployments.git). The image is available on DockerHub, so you can simply run the containers using Docker Compose:

```shell
# cd read-write-split-java-app
docker compose up -d
```

Alternatively, you can deploy the MariaDB database cluster on Docker Swarm to distribute the servers on multiple machines:

```shell
# (optional alternative, run only on a Docker Swarm)
docker stack deploy -c docker-compose.yaml mariadb
```

## Configure the database proxy (MariaDB MaxScale)

Access the MaxScale web GUI at http://localhost:8989. Log in using the following credentials:

* Username: `admin`
* Password: `mariadb`

In the **Dashboard**, click on **mdb_monitor** to access the monitor configuration and enable:

 * **automatic failover**, to automatically promote a a replica as a new primary when the primary fails, and
 * **auto rejoin**, to make a failed server automatically rejoin the cluster when it recovers

In the **Dashboard**, click **query_router_service** and enable:

 * **transaction reply**, to automatically retry in-flight transactions that might have failed during a failover

## Create the table

Use the MaxScale GUI to create the table. In the main menu, go to **Workspace**, click on **Run Queries**, and connect to the MariaDB database cluster using the following credentials:

* Listener name: `query_router_listener`
* Username: `user`
* Password: `Password123!`

Run the following SQL statement:

```sql
CREATE OR REPLACE TABLE demo.person(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    credit_card_number VARCHAR(20),
    write_server_id INT NOT NULL DEFAULT (@@server_id),
    read_server_id INT AS (@@server_id) VIRTUAL
);
```

## Run the web application

Build and run the Java web application using Maven:

```shell
# cd read-write-split-java-app
mvn
```

Alternatively you can build it with `mvn package -P production` and run it with `java -jar target/webapp.jar`.

Access the application in your browser at http://localhost:8080. Insert and update data and refresh the table to see how writes are performed on one server ID (the one corresponding to the primary node), but reads are load-balanced on other servers (replicas).

Stop the primary node:

```shell
docker stop server-1
```

> **Note:** Remember to configure MaxScale for automatic failover and rejoin!

MaxScale should promote a replica as the new primary and the web application should remain fully functional.

If you start the stopped container, it should rejoin the cluster as a replica.

To start it:

```shell
docker start server-1
```

To shutdown the database cluster run:

```shell
docker compose down
```

Add `-v` to the above command if you want to remove the related Docker volumes as well (you'll lose all the configuration and data).

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
