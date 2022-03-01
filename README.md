# Equipment Service
> This is an application for managing equipments and their information

> Equipments can be easily created, searched by using this application.

> Equipments are persisted in Elasticsearch

### Reference Documentation

Used technologies

* [Angular: 13.x.x](https://angular.io/)
* [Angular CLI: 8.x.x](https://cli.angular.io/)
* [Elasticseach 7.16.2](https://www.elastic.co/site-search/)
* [Java 17](https://www.oracle.com/java/)

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Prerequisites

* [Node.js](https://nodejs.org/) (version 12 or higher)
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (version 17)
* [NPM](https://www.npmjs.com/get-npm) (Version 8.3.*)
* [Docker](https://www.docker.com/)

### Let's get started,

* Install maven dependencies
* Install ui npm packages (* Go to `ui` directory and install dependencies by `npm install`)
* Elasticseach properties are set in
```
    ├── src/main/resources/
    │     ├── application.properties
```

#### Start Elasticsearch 
The application connects to elasticsearch instance. The details(url, port) are configured 
in application.yaml

To run elasticseach in docker container locally run below :
 ```docker run -p 9200:9200 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.16.2```
#### Start Backend
To run and access the backend application with only please follow below commands

```
    mvn spring-boot:run
```

Can be ran directly from IntelliJ IDEA by going to `src/main/java/com/saikat/api/countryservice/CountryServiceApplication.java` and clicking run.

Build Spring Boot Project with Maven

```maven package```

Or

    mvn install / mvn clean install

* To run tests

``` mvn test```

#### Start UI
Run `ng serve` from the ui folder for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

#### Build

Run `ng build` to build the project from `ui` directory. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

#### Running unit tests

Run `ng test` from `ui` folder to execute the unit tests via [Karma](https://karma-runner.github.io).

#### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
