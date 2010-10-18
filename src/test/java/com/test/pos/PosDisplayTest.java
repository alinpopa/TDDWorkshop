package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class PosDisplayTest {
		
	@Test
	public void errorMessageSentToTheDisplayShouldBeSet(){
		PosDisplay posDisplay = new PosDisplay();
		
		posDisplay.print("Error message");
		
		assertThat(posDisplay.getErrorMessage(), is("Error message"));
	}
	
	@Test
	public void priceSentToTheDisplayShouldBeSet(){
		PosDisplay posDisplay = new PosDisplay();
		
		posDisplay.print(100);
		
		assertThat(posDisplay.getErrorMessage(), is(""+100));
	}
}
