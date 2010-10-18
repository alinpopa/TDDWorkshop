package com.test.pos;

public class FederalTaxRate {

	private final double rate;
	
	public FederalTaxRate(int rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}

	public int simpleTaxFor(int amount) {
		return (int)(rate * amount);
	}

}
