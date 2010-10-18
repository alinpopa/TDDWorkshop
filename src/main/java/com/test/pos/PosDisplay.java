package com.test.pos;

public class PosDisplay implements Display {

	private String message;
	
	@Override
	public void print(int price) {
		setToPhysicalDisplay(price+"");
	}

	@Override
	public void print(String message) {
		setToPhysicalDisplay(message);
	}

	public String getErrorMessage() {
		return message;
	}

	private void setToPhysicalDisplay(final String message){
		this.message = message;
	}
	
}
