package com.test.pos;

public class BarcodeEvent {

	private final String barcode;
	
	public BarcodeEvent(String barcode) {
		this.barcode = barcode;
	}

	public String barcode() {
		return barcode;
	}

}
