package com.test.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProvincialTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		ProvincialTaxRate princialTaxRate = new ProvincialTaxRate(8);
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(8);
		
		Amount priceTax = princialTaxRate.taxFor(price);
		
		assertThat(priceTax, is(priceWithTax));
	} 
	
	@Test
	public void shouldNotApplyAnyTaxForTheGivenAmountWhenPercentageIsZero(){
		ProvincialTaxRate princialTaxRate = new ProvincialTaxRate(0);
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(0);
		
		Amount priceTax = princialTaxRate.taxFor(price);
		
		assertThat(priceTax, is(priceWithTax));
	}
}
