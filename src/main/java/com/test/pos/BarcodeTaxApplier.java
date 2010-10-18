package com.test.pos;

public class BarcodeTaxApplier implements TaxApplier{

	private final FederalTaxRate federalTaxRate;
	private final ProvincialTaxRate provincialTaxRate;
	
	public BarcodeTaxApplier(final FederalTaxRate federalTaxRate, final ProvincialTaxRate provincialTaxRate){
		this.federalTaxRate = federalTaxRate;
		this.provincialTaxRate = provincialTaxRate;
	}
	
	@Override
	public int apply(int price) {
		return price + applyFederalTaxRate(price) + applyProvincialTaxRate(price);
	}
	
	private int applyFederalTaxRate(final int price){
		return federalTaxRate.taxFor(price);
	}
	
	private int applyProvincialTaxRate(final int price){
		return provincialTaxRate.taxFor(price);
	}
}
