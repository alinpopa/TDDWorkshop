package com.test.pos;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class FederalTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(5);
		
		int rate = federalTaxRate.simpleTaxFor(100);
		
		assertThat(rate, is(5));
	}
	
	@Test
	public void shouldNotApplyAnyTaxForTheGivenAmountWhenPercentageIsZero(){
		FederalTaxRate federalTaxRate = new FederalTaxRate(0);
		
		int rate = federalTaxRate.simpleTaxFor(100);
		
		assertThat(rate, is(0));
	}
}
