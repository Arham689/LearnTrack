# JVM Basics

## JDK, JRE and JVM

**JVM (Java Virtual Machine)** - this is the thing which actually runs the java program. It reads the bytecode (.class files) and converts it into machine code for the computer we are using. Every OS (Windows, Mac, Linux) has its own JVM.

**JRE (Java Runtime Environment)** - JRE = JVM + the libraries (like java.util, java.lang etc) that are needed to run a java program. If you only want to run java programs and not write them, JRE is enough.

**JDK (Java Development Kit)** - JDK = JRE + development tools like `javac` (compiler), `java`, `jar`, debugger etc. We need JDK to write and compile java code.

So simply: **JDK > JRE > JVM**

## What is Bytecode

When we compile a .java file using `javac`, it does not directly become machine code. It becomes a `.class` file which has **bytecode**. Bytecode is like an in-between code which is not for any specific OS. The JVM understands bytecode and runs it.

## Write Once, Run Anywhere

Because java code is compiled into bytecode and not into machine code, the same .class file can run on any machine which has a JVM installed. So I can write and compile the program on my Mac and the same compiled file can run on Windows or Linux without changing anything.

This is what "write once, run anywhere" (WORA) means. The JVM takes care of the differences between operating systems, we dont need to worry about it.
