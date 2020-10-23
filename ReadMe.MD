# Armory Logger

Armory Logger

## Description

Sort log/text files with the format `[ISO-8601], [string message]` on a given directory or `/tmp` by default

## Getting Started

### Dependencies

* At least java 8 version 
* Maven

### Building

* run `mvn package`
* A jar executable should be generated at `/target/calculator.jar`

### Executing program

* to load log files at `/log` run `java -jar target/armory-logger.jar`
* to load log files at another directory run `java -jar target/armory-logger.jar [yourdirectory]`

example `java -jar target/armory-logger.jar /tmp/test-43412`

### Unit Testing 

* a quick load test can be run with `mvn test`

## Authors

Contributors names and contact info

Marco Martinez
[@MarcoStaccato](https://github.com/MarcoStaccato)