# Setup Instructions

## JDK Version

I used **OpenJDK 21 (Temurin 21.0.4)** on macOS.

To check java is installed:

```
java -version
javac -version
```

Output on my machine:

```
openjdk version "21.0.4" 2024-07-16 LTS
OpenJDK Runtime Environment Temurin-21.0.4+7 (build 21.0.4+7-LTS)
javac 21.0.4
```

## Hello World

First I wrote a simple Hello World program to check everything is working.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

Then compiled and ran it from terminal:

```
javac HelloWorld.java
java HelloWorld
```

Output:

```
Hello World
```

`javac` converts the .java file into a `HelloWorld.class` file (bytecode) and then `java` command runs that class file on the JVM.

I also used IntelliJ IDEA / VS Code to run it, just right click on the file and click Run.
