package com.test.pos;

public class FederalTaxRate {

	private final double rate;
	
	public FederalTaxRate(int rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}

	public Amount taxFor(Amount amount) {
		int taxValue = (int)(rate * amount.value());
		return new Amount(taxValue);
	}
}
