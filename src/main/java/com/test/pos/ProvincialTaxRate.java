package com.test.pos;

public class ProvincialTaxRate {
	private final double rate;
	
	public ProvincialTaxRate(int rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}

	public Amount taxFor(Amount amount) {
		int priceWithTaxValue = (int)(rate * amount.value());
		return new Amount(priceWithTaxValue);
	}
}
