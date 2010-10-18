package com.test.pos;

import static com.test.pos.BarCodeScanner.CODE_PRICE;

import org.junit.Test;
import org.mockito.Mockito;


public class BarCodeScannerTest {

	@Test
	public void priceShouldBeDisplayed() throws Exception {
		Display display = Mockito.mock(Display.class);
		BarCodeScanner barCodeScanner = new BarCodeScanner(display);

		barCodeScanner.scan("code");

		Mockito.verify(display).print(CODE_PRICE);
	}
}
