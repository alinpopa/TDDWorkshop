package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class BarcodeEventTest {
	
	@Test
	public void passedInBarcodeShouldBeReturnedAsIsFromEvent(){
		BarcodeEvent barCodeEvent = new BarcodeEvent("test-barcode");
		
		String barcode = barCodeEvent.barcode();
		
		assertThat(barcode,is("test-barcode"));
	}
}
