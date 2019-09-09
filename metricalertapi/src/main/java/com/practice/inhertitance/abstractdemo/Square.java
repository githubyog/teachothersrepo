package com.practice.inhertitance.abstractdemo;

public class Square extends Shape {
	
	private Number side;
	
	public Square() {
		
	}

	public Square(Number side) {
		this.side = side;
	}

	@Override
	public void setDimensions(Number[] dimensions) {
		if(dimensions == null || dimensions.length != 1) {
			throw new NotSupportedException("Square shape expects only one dimesion");
		}
		side = dimensions[0];
	}

	@Override
	public Number calculateArea() {
		return side.doubleValue() * side.doubleValue();
	}

	@Override
	public Number calculatePerimeter() {	
		return side.doubleValue() * 4;
	}

}
