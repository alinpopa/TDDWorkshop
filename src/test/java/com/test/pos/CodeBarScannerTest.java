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
		
		verify(display).print("some error");
	}
	
	@Test
	public void shouldDisplayErrorOnDisplayWhenCodeBarIsNull(){
		Display display = mock(Display.class);
		CodeBarScanner codeBarScanner = new CodeBarScanner(display);
		
		codeBarScanner.scan(null);
		
		verify(display).print("some error");
	}
	
	@Test
	public void shouldDisplayPriceForTheGivenBarCode(){
		Display display = mock(Display.class);
		CodeBarScanner codeBarScanner = new CodeBarScanner(display);
		
		codeBarScanner.scan("valid barcode");
		
		verify(display).print(100L);
	}
}
