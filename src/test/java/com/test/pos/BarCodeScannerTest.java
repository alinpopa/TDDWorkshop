package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class BarCodeScannerTest {

	@Test
	public void priceShouldBeDisplayedForOneCode() throws Exception {
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		when(repository.get("barcode")).thenReturn(100L);
		BarCodeScanner barCodeScanner = new BarCodeScanner(display,repository);

		barCodeScanner.scan("barcode");

		Mockito.verify(display).print(100L);
	}

	@Test(expected = InvalidBarCodeException.class)
	public void shouldHandleNullBarCode() throws Exception{
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		when(repository.get(null)).thenThrow(new InvalidBarCodeException());
		BarCodeScanner barCodeScanner = new BarCodeScanner(display,repository);

		barCodeScanner.scan(null);
	}
	
	@Test(expected = InvalidBarCodeException.class)
	public void shouldHandleInvalidBarCode() throws Exception{
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		when(repository.get("invalid code")).thenThrow(new InvalidBarCodeException());
		BarCodeScanner barCodeScanner = new BarCodeScanner(display,repository);

		barCodeScanner.scan("invalid code");
	}
}
