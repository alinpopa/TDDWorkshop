package com.test.pos;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class FederalTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(5);
		Amount price = new Amount(100);
		Amount taxValue = new Amount(5);
		
		Amount priceTax = federalTaxRate.taxFor(price);
		
		assertThat(priceTax, is(taxValue));
	}
	
	@Test
	public void shouldNotApplyAnyTaxForTheGivenAmountWhenPercentageIsZero(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(0);
		Amount price = new Amount(100);
		Amount taxValue = new Amount(0);
		
		Amount priceTax = federalTaxRate.taxFor(price);
		
		assertThat(priceTax, is(taxValue));
	}
}
