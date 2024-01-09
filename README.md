
# WordCounter Library & Web Service

## Overview

The WordCounter project consists of a Java library and a Spring Boot-based web service designed to count occurrences of words. This is in response to the exercise provided to assess my skills.

## Considerations

- **Thread Safety**: I utilised `ConcurrentHashMap` for storing words and their counts to ensure thread-safe operations in a concurrent environment.
- **Efficiency**: I chose the use of `ConcurrentHashMap` for this. It provides the `merge` method which is very efficient for counting words.
- **Mock Translator**: I have provided a mock translator to enable the Springboot Dependency Injection to work and enable the app to run.
- **RESTful API Design**: Exposed the functionality through a REST API, adhering to standard practices and allowing easy integration for clients.

## Compilation and Testing

### Compiling the Library

To compile the library, navigate to the project root directory and run:

```bash
mvn clean install
```

This command compiles the project and installs the package into the local repository (It should also run the tests).

### Running Tests (Specifically)

To run the tests, execute the following command in the project root:

```bash
mvn test
```

This will run all the unit tests in the project.

## Running the Web Service

To start the web service, run the following command:

```bash
java -jar target/CounterChallenge-0.0.1-SNAPSHOT.jar
```

Replace `CounterChallenge-0.0.1-SNAPSHOT.jar` with the actual name of your generated JAR file if it is different. But ideally it should be same

## Using the API

The base URL for the API is `http://127.0.0.1:8080/api/v1/wordcounter` (As long as you are running it locally). If you are running on a different environment please update the IP address.

### Adding Words

To add words to the counter, use the following `curl` command:

```bash
curl -X POST http://127.0.0.1:8080/api/v1/wordcounter -H "Content-Type: application/json" -d '["word1", "word2"]'
```

### Getting Word Count

To get the count of a specific word, use the following `curl` command:

```bash
curl http://127.0.0.1:8080/api/v1/wordcounter?word=teddy
```

