package com.test.pos;

public class BarCodeScanner {

	public static final long CODE_PRICE = 123L;
	private final Display display;
	
	public BarCodeScanner(final Display display) {
		this.display = display;
	}

	public void scan(final String barCode) {
		display.print(CODE_PRICE);
	}

}
