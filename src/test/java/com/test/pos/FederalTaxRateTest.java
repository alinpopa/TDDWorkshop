package com.test.pos;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class FederalTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(5);
		
		int priceWithTax = federalTaxRate.taxFor(100);
		
		assertThat(priceWithTax, is(5));
	}
	
	@Test
	public void shouldNotApplyAnyTaxForTheGivenAmountWhenPercentageIsZero(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(0);
		
		int priceWithTax = federalTaxRate.taxFor(100);
		
		assertThat(priceWithTax, is(0));
	}
}
