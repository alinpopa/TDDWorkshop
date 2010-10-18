package com.test.pos;

public class ProvincialTaxRate {
	private final double rate;
	
	public ProvincialTaxRate(int rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}

	public int simpleTaxFor(int amount) {
		return (int)(rate * amount);
	}
}
