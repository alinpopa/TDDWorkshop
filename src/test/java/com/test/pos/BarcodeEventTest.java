package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class BarcodeEventTest {
	
	@Test
	public void asStringShouldReturnTheContentValueOfTheEvent(){
		BarcodeEvent barCodeEvent = new BarcodeEvent("test-barcode");
		
		String barcodeValue = barCodeEvent.asString();
		
		assertThat(barcodeValue,is("test-barcode"));
	}
}
