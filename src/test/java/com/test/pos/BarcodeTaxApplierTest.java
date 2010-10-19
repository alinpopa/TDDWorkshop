package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class BarcodeTaxApplierTest {

	@Test
	public void shouldNotApplyAnyTaxWhenBothFederalAndProvincialTaxRatesAreZero(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(0),
				new ProvincialTaxRate(0));
		Amount price = new Amount(100);
		
		Amount priceWithoutFederalTax = barcodeTaxApplier.calculateFor(new PstFreeProduct("barcode1", price, ""));
		
		assertThat(priceWithoutFederalTax, is(price));
	}
	
	@Test
	public void shouldApplyOnlyFederalTaxRateWhenProvincialTaxRateIsZero(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(5),
				new ProvincialTaxRate(0));
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(105);
		
		Amount priceWithFederalTax = barcodeTaxApplier.calculateFor(new PstFreeProduct("barcode1", price, ""));
		
		assertThat(priceWithFederalTax, is(priceWithTax));
	}
	
	@Test
	public void shouldApplyOnlyProvincialTaxRateWhenFederalTaxRateIsZero(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(0),
				new ProvincialTaxRate(8));
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(108);
		
		Amount priceWithoutProvincialTaxRateApplied = barcodeTaxApplier.calculateFor(new PstProduct("barcode1", price, ""));
		
		assertThat(priceWithoutProvincialTaxRateApplied, is(priceWithTax));
	}
	
	@Test
	public void shouldApplyBothFederalAndProvincialTaxRates(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(5),
				new ProvincialTaxRate(8));
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(113);
		
		Amount priceWithoutProvincialTaxRateApplied = barcodeTaxApplier.calculateFor(new PstProduct("barcode1", price, ""));
		
		assertThat(priceWithoutProvincialTaxRateApplied, is(priceWithTax));
	}
}
