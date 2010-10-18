package com.test.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProvincialTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		ProvincialTaxRate princialTaxRate = new ProvincialTaxRate(8);
		
		int priceWithTax = princialTaxRate.taxFor(100);
		
		assertThat(priceWithTax, is(8));
	} 
	
	@Test
	public void shouldNotApplyAnyTaxForTheGivenAmountWhenPercentageIsZero(){
		ProvincialTaxRate princialTaxRate = new ProvincialTaxRate(0);
		
		int priceWithTax = princialTaxRate.taxFor(100);
		
		assertThat(priceWithTax, is(0));
	}
}
