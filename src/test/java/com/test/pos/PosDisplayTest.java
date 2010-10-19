package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class PosDisplayTest {
	
	@Test
	public void priceShouldBeSentToTheConfiguredDevice(){
		Device device = mock(Device.class);
		PosDisplay posDisplay = new PosDisplay(device);
		
		posDisplay.printPrice(new Amount(100));
		
		verify(device).write("100");
	}
	
	@Test
	public void errorMessageShouldBeSentToTheConfiguredDevice(){
		Device device = mock(Device.class);
		PosDisplay posDisplay = new PosDisplay(device);
		
		posDisplay.printPriceNotFoundMessage("Invalid product barcode [xyz]");
		
		verify(device).write("Invalid product barcode [xyz]");
	}
}
