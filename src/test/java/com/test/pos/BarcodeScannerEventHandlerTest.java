package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class BarcodeScannerEventHandlerTest {

	private POS pointOfSale;

	private BarcodeScannerEventHandler barcodeScannerEventHandler;
	
	@Before
	public void setUp() {
		pointOfSale = mock(PointOfSale.class);
		
		barcodeScannerEventHandler = new BarcodeScannerEventHandler(pointOfSale);
	}

	@Test
	public void shouldDisplayThePriceForTheProvidedBarcode() {
		barcodeScannerEventHandler.handle(new BarcodeEvent("valid"));

		verify(pointOfSale).onBarcodeScan("valid");
	}

	@Test(expected = InvalidBarCodeEventException.class)
	public void shouldNotHandleNullBarCodeEvents() {
		barcodeScannerEventHandler.handle(null);
	}
}
