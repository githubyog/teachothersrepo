package com.practice.inhertitance.abstractdemo;

public class Rectangle extends Shape {
	
	private Number length;
	
	private Number breadth;
	
	public Rectangle() {
		
	}

	public Rectangle(Number length, Number breadth) {
		this.length = length;
		this.breadth = breadth;
	}

	@Override
	public void setDimensions(Number[] dimensions) {
		if(dimensions == null || dimensions.length != 2) {
			throw new NotSupportedException("Rectangle shape expects only two dimesion");
		}
		length = dimensions[0];
		breadth = dimensions[1];
	}

	@Override
	public Number calculateArea() {
		return length.doubleValue() * breadth.doubleValue();
	}

	@Override
	public Number calculatePerimeter() {	
		return length.doubleValue() * 2 + breadth.doubleValue() *2;
	}

}
