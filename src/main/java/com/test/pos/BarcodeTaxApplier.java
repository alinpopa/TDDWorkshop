package com.test.pos;

public class BarcodeTaxApplier implements TaxApplier {

	private final FederalTaxRate federalTaxRate;
	private final ProvincialTaxRate provincialTaxRate;
	
	public BarcodeTaxApplier(final FederalTaxRate federalTaxRate, final ProvincialTaxRate provincialTaxRate){
		this.federalTaxRate = federalTaxRate;
		this.provincialTaxRate = provincialTaxRate;
	}
	
	@Override
	public Amount calculateFor(final PstFreeProduct product){
		Amount federalTaxRate = applyFederalTaxRate(product);
		return product.netPrice().plus(federalTaxRate);
	} 
	
	@Override
	public Amount calculateFor(final PstProduct product){
		Amount federalTaxRate = applyFederalTaxRate(product);
		Amount provincialTaxRate = applyProvincialTaxRate(product);
		return product.netPrice().plus(federalTaxRate).plus(provincialTaxRate);
	}
	
	private Amount applyFederalTaxRate(final Product product){
		return federalTaxRate.taxFor(product.netPrice());
	}
	
	private Amount applyProvincialTaxRate(final Product product){
		return provincialTaxRate.taxFor(product.netPrice());
	}
}
