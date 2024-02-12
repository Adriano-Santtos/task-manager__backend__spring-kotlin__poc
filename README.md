# poc-task-manager-api

## Overview

This is an API to a task manager, using Spring with Kotlin.
The purpose of this POC is use: 

- [Kotlin](https://kotlinlang.org/)
- [Spring](https://spring.io/)

---
## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Running Locally](#running-locally)
- [Documentation](#documentation)
---

## Getting Started

### Prerequisites

Ensure you have the following prerequisites installed before setting up and running the API:

- [Java Development Kit (JDK)](https://adoptopenjdk.net/)
- [Docker](https://www.docker.com/)
- [Gradle](https://gradle.org/install/)

### Running Locally

1. Clone the repository:

    ```bash
    git clone https://github.com/Adriano-Santtos/poc-task-manager-spring-kotlin.git
    ```

3. Run the Docker container inside the project directory:

    ```bash
    docker compose up -d
    ```
   
4. Run the application using Gradle:

    ```bash
    ./gradlew bootRun
    ```

## Documentation


After the application runs locally, access the swagger's endpoint to view details of available endpoints and your functionalities:
<http://localhost:8080/swagger-ui/index.html>
