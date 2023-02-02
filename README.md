# Kalah Game Exercise

The game provides a Kalah board and a number of seeds or counters. The board has 6 small pits, called houses, on each side; and a big pit, called an end zone or store, at each end. The object of the game is to capture more seeds than one's opponent.
<br>
Game on Wikipedia: https://en.wikipedia.org/wiki/Kalah.


### Built With
- Java 11
- Spring Boot 2.7.3
- Spring MVC
- Lombok
- Junit
- Jacoco code coverage
- Swagger
- Maven
- H2 Database
- angular 15

<br>
<h4>Unit Tests coverage</h4>
After building the project the report generated under path

```sh
target/site/jacoco/index.html
```

### Getting Started
To get a local copy up and running please follow these example steps.

- Build and run backend first
- Run angular UI second

### Prerequisites
1. JDK 11 or higher
2. Maven
3. Docker

<br>
Please open a command line (or terminal) and navigate to the folder where you have the
project files (project root directory) <b>kalah</b>. then perform the following commands
<br>
<br>

## Build and run backend


Change directory to Kalah backend project
```sh
cd kalah
```

Building form source code and generate the jar file

```sh
mvn clean package
```

<br>
Building a docker image for spring boot project

```sh
docker build -t kalah .
```

<br>
Start the image container (if you want to change the port please change UI config accordingly)

```sh
docker run -p 8082:8082 kalah
```


<br>

**[Swagger Page](http://localhost:8082/swagger-ui/) (http://localhost:8082/swagger-ui/)**
<br>
<br>
## Run angular UI

Change directory to Kalah-ui directory project
```sh
cd ../kalah-ui/
```

Run npm install to install dependencies 
```sh
npm i
```

Then ng serve to run the ui project dev server
```sh
ng serve
```
the project will be running on the default port
``
http://localhost:4200/
``

**[Kalah-Game](http://localhost:4200) (http://localhost:4200)**

