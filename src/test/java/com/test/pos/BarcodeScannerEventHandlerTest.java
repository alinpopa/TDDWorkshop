package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BarcodeScannerEventHandlerTest {
	
	private static final String VALID_BARCODE = "valid barcode";
	private static final String INVALID_BARCODE = "invalid barcode";

	@Test
	public void shouldDisplayErrorOnDisplayWhenWrongBarCodeIsPassedIn(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		stub(repository.get(INVALID_BARCODE)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler codeBarScanner = new BarcodeScannerEventHandler(display, repository, taxApplier);
		
		codeBarScanner.handle(new BarcodeEvent(INVALID_BARCODE));
		
		verify(display).print("No product found with barcode ["+INVALID_BARCODE+"]");
	}
	
	@Test(expected = InvalidBarCodeEventException.class)
	public void shouldNotHandleNullBarCodeEvents(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		stub(repository.get(null)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler codeBarScanner = new BarcodeScannerEventHandler(display, repository, taxApplier);
		
		codeBarScanner.handle(null);
	}
	
	@Test
	public void shouldDisplayPriceForTheGivenBarCode(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		stub(repository.get(VALID_BARCODE)).toReturn(100);
		stub(taxApplier.apply(100)).toReturn(100);
		BarcodeScannerEventHandler codeBarScanner = new BarcodeScannerEventHandler(display,repository, taxApplier);
		
		codeBarScanner.handle(new BarcodeEvent(VALID_BARCODE));
		
		verify(display).print(100);
	}
	
	@Test
	public void displayedPriceShouldHaveTheFederalTaxApplied(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		stub(repository.get(VALID_BARCODE)).toReturn(100);
		stub(taxApplier.apply(100)).toReturn(105);
		BarcodeScannerEventHandler codeBarScanner = new BarcodeScannerEventHandler(display,repository,taxApplier);
		
		codeBarScanner.handle(new BarcodeEvent(VALID_BARCODE));
		
		verify(display).print(105);
	}
}
