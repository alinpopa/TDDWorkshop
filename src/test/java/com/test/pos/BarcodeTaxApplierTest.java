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
		
		int priceWithoutFederalTax = barcodeTaxApplier.apply(100);
		
		assertThat(priceWithoutFederalTax, is(100));
	}
	
	@Test
	public void shouldApplyOnlyFederalTaxRateWhenProvincialTaxRateIsZero(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(5),
				new ProvincialTaxRate(0));
		
		int priceWithFederalTax = barcodeTaxApplier.apply(100);
		
		assertThat(priceWithFederalTax, is(105));
	}
	
	@Test
	public void shouldApplyOnlyProvincialTaxRateWhenFederalTaxRateIsZero(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(0),
				new ProvincialTaxRate(8));
		
		int priceWithoutProvincialTaxRateApplied = barcodeTaxApplier.apply(100);
		
		assertThat(priceWithoutProvincialTaxRateApplied, is(108));
	}
	
	@Test
	public void shouldApplyBothFederalAndProvincialTaxRates(){
		BarcodeTaxApplier barcodeTaxApplier = new BarcodeTaxApplier(
				new FederalTaxRate(5),
				new ProvincialTaxRate(8));
		
		int priceWithoutProvincialTaxRateApplied = barcodeTaxApplier.apply(100);
		
		assertThat(priceWithoutProvincialTaxRateApplied, is(113));
	}
}
