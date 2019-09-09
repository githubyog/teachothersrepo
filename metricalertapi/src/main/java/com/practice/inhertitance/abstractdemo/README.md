# Introduction
In this package we are trying to explain Abstract classes and the Java Wrapper classes.

## Abstract class
This is a special type of class with at least method not implemented and just define the method and it is defined as abstract.

public abstract void setDimensions(Number[] dimensions);

Basically it is defined as abstract  and the return type with method name and the input parameters.

Here the return type is void

Method name is setDimensions

Parameters are Number[] dimensions

Please note it is ended immediately by Semicolon (";") and not like by { , statements and then followed by }

This is what we mean as not implemented.

## Wrapper classes

Java has the Wrapper classes for each primitive data types.
Here is the below list which shows the primitive data type to Wrapper class.


| Primitive data type | Wrapper class        |
| ------------------- | -------------------- |
| byte                | java.lang.Byte       |
| short               | java.lang.Short      |
| int                 | java.lang.Integer    |
| long                | java.lang.Long       |
| float               | java.lang.Float      |
| double              | java.lang.Double     |
| boolean             | java.lang.Boolean    |

All of the above classes have the common super class which is java.lang.Number

Please refer the Number API below:

https://docs.oracle.com/javase/8/docs/api/java/lang/Number.html

In case of Square right now I declared it as a Number object which gives the flexibility to use any data type Object.
If it is declared as primitive data type for example int then it is restricted to be int only.

Interesting thing in Java 5 and above is you can directly assign the primitive data type value to Wrapper class object.

Integer i = 5;  //This is possible in Java 5 and above

Integer i = new Integer(5);  //This Java 2 syntax above line will be a compilation issue in Java 2.


This also gives me some flexibility to declare Number array to assign different data types

Number[] rectangleDimensions = {12.5, 4};  //here you can see we are able to assign double and int to single variable array - 12.5 is double and 4 is integer

int [] x = { 1.34, 4 }; // This is a compilation issue since you cannot assign 1.34 is double

