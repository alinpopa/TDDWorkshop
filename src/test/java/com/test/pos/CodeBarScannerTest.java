package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class CodeBarScannerTest {

	@Test
	public void shouldDisplayErrorOnDisplayWhenWrongBarCodeIsPassedIn(){
		Display display = mock(Display.class);
		CodeBarScanner codeBarScanner = new CodeBarScanner(display);

		codeBarScanner.scan("invalid barcode");

    // When you scan a barcode with no price, please print
    // the message "No product found with barcode [scanned-barcode]"
		verify(display).print("some error");
	}

	@Test
	public void shouldDisplayErrorOnDisplayWhenCodeBarIsNull(){
		Display display = mock(Display.class);
		CodeBarScanner codeBarScanner = new CodeBarScanner(display);

    // The manual for the barcode scanner claims that the
    // scanner will never send you null, but at worst, an
    // empty string. You can decide whether to keep this test.
		codeBarScanner.scan(null);

    // If you will provide an error message for this case, then
    // I'd like a more detailed error message, such as
    // "Scanning barcode seems to have failed: received null barcode."
		verify(display).print("some error");
	}

	@Test
	public void shouldDisplayPriceForTheGivenBarCode(){
		Display display = mock(Display.class);
		CodeBarScanner codeBarScanner = new CodeBarScanner(display);

    // How do you know that the price should be 100?
		codeBarScanner.scan("valid barcode");

		verify(display).print(100L);
	}
}
