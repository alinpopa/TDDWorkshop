package com.test.pos;

public class BarcodeEvent {

	private final String barcodeValue;
	
	public BarcodeEvent(String barcodeValue) {
		this.barcodeValue = barcodeValue;
	}

	public String asString() {
		return barcodeValue;
	}

}
