package com.test.pos;

public class PosDisplay implements Display {

	private String message;
	
	@Override
	public void print(Amount price) {
		setToPhysicalDisplay(price.toString());
	}

	@Override
	public void print(String message) {
		setToPhysicalDisplay(message);
	}

	@Override
	public String message() {
		return message;
	}

	private void setToPhysicalDisplay(final String message){
		this.message = message;
	}
	
}
