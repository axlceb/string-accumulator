# String Accumulator - A simple maven demo project
But I am a big advocate of Gradle and I always push to use Gradle before Maven :)

## Dependencies
- lombok
- jackson
- apache.commons
- junit
- mockito
- slf4j

This is a single java project for a demo purpose. You will find the example running the `App.java` file

## The Task:

````
String Accumulator
1. Create a simple string calculator with a method int add(String numbers)
    a. The method can take 0, 1 or 2 numbers and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
    b. Start with the simplest test case of an empty string and move to 1 and 2 numbers.
2. Allow the add method to handle an unknown amount of numbers.
3. Allow the add method to handle new lines between numbers (instead of commas).
    a. The following input is ok: “1\n2,3” (will equal 6).
    b. The following input is NOT ok: “1,\n” (don’t need to prove it - just clarifying).
4. Support different delimiters.
    a. To change a delimiter, the beginning of the string will contain a separate line that looks like this: “//<delimiter>\n<numbers...>”, for example “//;\n1;2” should return 3 where the delimiter is ‘;’.
    b. The first line is optional, all existing scenarios should still be supported.
5. Calling add with a negative number will throw an exception with the message “negatives not allowed” - and the negative that was passed.
a. If there are multiple negatives, show all of them in the exception message.
6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.
7. Delimiters can be of any length, for example: “//***\n1***2***3” should return 6.
8. Allow multiple delimiters like this: “//delim1|delim2\n” (with a “|” separating delimiters), for example “//*|%\n1*2%3” should return 6.
9. Make sure you can also handle multiple delimiters with length longer than one character.
````

## Setup lombok for intellij

If you are using intellij, need to activate annotations processor:
    Settings -> Compiler -> Annotation Processors

Now install lombok plugin:

    Preferences -> Plugins
    Click Browse repositories...
    Search for "Lombok Plugin"
    Install
    Restart IntelliJ

## Dependencies

See the pom.xml file to see what line is doing what

## Simple commands to try with maven

### Create jar file with dependencies with maven:
```
mvn package
```

### Run project:
```
java -jar string-accumulator-1.0-SNAPSHOT-jar-with-dependencies.jar
```


Project structure:
```
.
├── README.md
├── pom.xml
└── src
    └── main
        └── java
            └── org
                └── axlceb
                    ├── App.java
                    ├── AppConfig.java
                    ├── service
                    │   ├── StringService.java
                    │   └── StringServiceImp.java
                    └── exeption
                        └── NegativesNotAllowedException.java
```
## Test cases
```
Task 1: ""
Task 1: "1"
Task 1: "1,2"
Task 2: "1,1 1,1 1"
Task 3: "1\n2,3"
Task 4: "//;\n1;2"
Task 4: "1,1 1,1\n1"
Task 5: "0,-1,2,3,-4,5"
Task 6: "2,1001"
Task 7: "//***\n1***2***3"
Task 8: "//*|%\n1*2%3"
Task 9: "//delim1|delim2\n1delim12delim23"
```
