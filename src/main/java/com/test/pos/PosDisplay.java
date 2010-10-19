package com.test.pos;

public class PosDisplay implements Display {

	private final Device device;
	
	public PosDisplay(final Device device) {
		this.device = device;
	}

	@Override
	public void printPrice(Amount price) {
		device.write(price.toString());
	}

	@Override
	public void printPriceNotFoundMessage(String message) {
		device.write(message);
	}
}
