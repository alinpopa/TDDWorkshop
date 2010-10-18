package com.test.pos;

public class BarCodeScanner {

	public static final String CODE_01 = "code1";
	public static final String CODE_02 = "code2";
	public static final long CODE_PRICE_01 = 100L;
	public static final long CODE_PRICE_02 = 200L;
	
	private final Display display;
	private final Repository repository;
	
	public BarCodeScanner(final Display display, final Repository repository) {
		this.display = display;
		this.repository = repository;
	}

	public void scan(final String barCode) {
		final Long price = repository.get(barCode);
		display.print(price);
	}

}
