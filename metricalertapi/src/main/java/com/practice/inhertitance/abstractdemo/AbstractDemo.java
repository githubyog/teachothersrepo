package com.practice.inhertitance.abstractdemo;

public class AbstractDemo {
	
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		
		Number[] rectangleDimensions = {12.5, 4};
		rectangle.setDimensions(rectangleDimensions);
		System.out.println("Calculated area : "+rectangle.calculateArea());
		Shape square = new Square();
		Number[] squareDimensions = {4};
		square.setDimensions(squareDimensions);
		System.out.println("Calculated area : "+square.calculateArea());

	}

}
