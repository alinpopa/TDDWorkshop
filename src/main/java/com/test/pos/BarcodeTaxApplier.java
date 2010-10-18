package com.test.pos;

public class BarcodeTaxApplier implements TaxApplier{

	private final int federalTaxRate;
	private final int provincialTaxRate;
	
	public BarcodeTaxApplier(final int federalTaxRate, final int provincialTaxRate){
		this.federalTaxRate = federalTaxRate;
		this.provincialTaxRate = provincialTaxRate;
	}
	
	@Override
	public int apply(int price) {
		return price + applyFederalTaxRate(price) + applyProvincialTaxRate(price);
	}
	
	private int applyFederalTaxRate(final int price){
		return (price * federalTaxRate / 100);
	}
	
	private int applyProvincialTaxRate(final int price){
		return (price * provincialTaxRate / 100);
	}
}
