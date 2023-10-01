Creating a README for a Spring WebFlux API with hexagonal architecture, Docker Compose, and Maven involves providing comprehensive instructions on how to set up, run, and use your API. Below is a sample README that you can use as a starting point for your project:

---

# Spring WebFlux API with Hexagonal Architecture and Docker Compose

This repository contains a sample Spring WebFlux API built using the hexagonal architecture pattern, managed with Maven, and Docker Compose for easy deployment and testing. The hexagonal architecture promotes a clean separation of concerns, making the application more maintainable and testable.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Building the Application](#building-the-application)
    - [Running the Application](#running-the-application)
- [Testing](#testing)
- [Docker Compose](#docker-compose)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you can run this application, make sure you have the following dependencies installed on your machine:

- [Java Development Kit (JDK) 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

Follow these steps to set up and run the application locally:

### Building the Application

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/jugurta/hexagonal.git
   cd hexagonal
   ```

2. Build the application using Maven:

   ```bash
   mvn clean package
   ```

### Running the Application

You can run the application either directly on your local machine or using Docker Compose.

#### Local Development

To run the application locally, execute the following command:

```bash
java -jar target/hexagonal.jar
```

The API will be available at [http://localhost:8080](http://localhost:8080).

#### Using Docker Compose

To run the application using Docker Compose, execute the following command:

```bash
docker-compose up
```

The API will be available at [http://localhost:8080](http://localhost:8080).

## Testing

To run the unit tests and integration tests, use the following Maven command:

```bash
mvn test
```

## Docker Compose

The `docker-compose.yml` file in the project root defines the services required to run the application and any necessary dependencies (e.g., a database). You can customize this file to suit your needs.

To start the application and its dependencies using Docker Compose, run the following command:

```bash
docker-compose up
```

To stop the containers, use:

```bash
docker-compose down
```

## Contributing

If you'd like to contribute to this project, please follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to customize this README to fit the specifics of your project, including any additional information, configuration options, or deployment instructions.