package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class AmountTest {

	@Test
	public void shouldBeAbleToAddTwoPrices(){
		Amount price = new Amount(100);
		
		Amount newPrice = price.plus(new Amount(200));
		
		assertThat(newPrice, is(newPrice));
	}
	
	@Test
	public void displayedStringOfThePriceShouldBeTheStringRepresentationOfItsValue(){
		Amount price = new Amount(100);
		
		String priceAsString = price.toString();
		
		assertThat(priceAsString, is("100"));
	}
}
