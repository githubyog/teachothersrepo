package com.practice.declarations.variable.differentPackage;

import com.practice.declarations.variable.ClassVariableDeclaration;
//Also note since ClassVariableDeclaration is in a different package we need to import the class first in the line number 3

public class DifferentPackageDifferentClass {

	
	public static void main(String [] args) {
		
		ClassVariableDeclaration object = new ClassVariableDeclaration();
		ClassVariableDeclaration diffObject = new ClassVariableDeclaration();
		
		//These below are instance variables, To access or set the value of the variable
		//First you must create a object using new operator and the constructor like above
		
		
		//Uncomment the next line to see the compilation error that the private variable is not accessible outside of class
		//object.privateScopeNumber = 2; 
		
		//Also note any whole number is a integer by default in Java
		
		//Also note any decimal number is a double by default in Java
		//If we have assign to a float variable then we need to type cast first.
		//Type casting is making the value compatible to the variable
		//Here 4.5 is double so type casting to float so that it is compatible to float
		
		//Uncomment the next line to see the compilation error that the default variable is not accessible outside of Package
		//object.defaultScopeNumber = (float)4.5;
		
		//Uncomment the next line to see the compilation error that the protected variable is not accessible outside of Package for non child class
		//object.protectedScopeNumber = 6.09;
		
		object.publicScopeLiteral = "Welcome";  //Proof that public is accessible in different package and unrelated class.
		
		//For static we can directly access or set as given without creating Object.
		
		//Uncomment the next line to see the compilation error that the private static variable is not accessible outside of class
		//ClassVariableDeclaration.staticPrivateScopeNumber = 5;
		
		//Uncomment the next line to see the compilation error that the default variable is not accessible outside of Package
		//ClassVariableDeclaration.staticDefaultScopeNumber = (float)8.77;
		
		//Uncomment the next line to see the compilation error that the protected variable is not accessible outside of Package for non child class
		//ClassVariableDeclaration.staticProtectedScopeNumber = 7.077;
		
		
		ClassVariableDeclaration.staticPublicLiteral ="This is a Static String";//Proof that public is accessible in different package and unrelated class.
		
		System.out.println("Trying to access the static variables set without Object"+ClassVariableDeclaration.staticPublicLiteral);

		//It is not a good practice to access a static variable using the object, just showed for demonstration.
		System.out.println("Actually we did not set against the object, static is shared across objects and here is the proof = "+object.staticPublicLiteral);
		System.out.println("Actually we did not set against the diffObject, static is shared across objects and here is the proof = "+diffObject.staticPublicLiteral);
		
		
	}

}
