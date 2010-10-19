package com.test.pos;

public class BarcodeTaxApplier implements TaxApplier{

	private final FederalTaxRate federalTaxRate;
	private final ProvincialTaxRate provincialTaxRate;
	
	public BarcodeTaxApplier(final FederalTaxRate federalTaxRate, final ProvincialTaxRate provincialTaxRate){
		this.federalTaxRate = federalTaxRate;
		this.provincialTaxRate = provincialTaxRate;
	}
	
	@Override
	public Amount apply(Amount price) {
		return price.plus(applyFederalTaxRate(price)).plus(applyProvincialTaxRate(price));
	}
	
	private Amount applyFederalTaxRate(final Amount price){
		return federalTaxRate.taxFor(price);
	}
	
	private Amount applyProvincialTaxRate(final Amount price){
		return provincialTaxRate.taxFor(price);
	}
}
