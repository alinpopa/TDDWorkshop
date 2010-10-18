package com.test.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProvincialTaxRateTest {
	
	@Test
	public void shouldCalculateTaxForTheGivenAmount(){
		ProvincialTaxRate princialTaxRate = new ProvincialTaxRate(8);
		
		int rate = princialTaxRate.simpleTaxFor(100);
		
		assertThat(rate, is(8));
	} 
	
}
