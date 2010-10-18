package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BarcodeScannerEventHandlerTest {
	
	private static final String PRODUCT_01 = "product01";
	private static final String INVALID_BARCODE = "invalid barcode";

	@Test
	public void shouldDisplayErrorOnDisplayWhenWrongBarCodeIsPassedIn(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(INVALID_BARCODE)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(INVALID_BARCODE));
		
		verify(display).print("No product found with barcode ["+INVALID_BARCODE+"]");
	}
	
	@Test(expected = InvalidBarCodeEventException.class)
	public void shouldNotHandleNullBarCodeEvents(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(null)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(null);
	}
	
	@Test
	public void shouldDisplayPriceForTheGivenBarCode(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(PRODUCT_01)).toReturn(100);
		stub(taxApplier.apply(100)).toReturn(100);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display,repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).print(100);
	}
	
	@Test
	public void displayedPriceShouldHaveTaxesApplied(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(PRODUCT_01)).toReturn(100);
		stub(taxApplier.apply(100)).toReturn(105);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).print(105);
	}
	
	@Test
	public void shouldApplyTaxesToTheGivenPrice(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(PRODUCT_01)).toReturn(100);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(taxApplier).apply(100);
	}
	
	@Test
	public void shouldGenerateCashReportForScannedProducts(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		stub(repository.get(PRODUCT_01)).toReturn(100);
		stub(taxApplier.apply(100)).toReturn(105);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		barcodeScanner.pay();
		
		verify(printer).printCashReport();
	}
}
