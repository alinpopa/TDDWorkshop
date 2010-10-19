package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import org.junit.Test;

public class BarcodeScannerEventHandlerTest {
	
	private static final String PRODUCT_01 = "product01";
	private static final String PRODUCT_02 = "product02";
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
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display,repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).print(price);
	}
	
	@Test
	public void displayedPriceShouldNotHaveTaxesApplied(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).print(priceWithTax);
	}
	
	@Test
	public void shouldApplyTaxesToTheGivenPrice(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		Amount price = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(taxApplier).apply(price);
	}
	
	@Test
	public void shouldGenerateCashReportForScannedProductsWhenPaymentIsMade(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(105);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		barcodeScanner.pay();
		
		verify(printer).printCashReport();
	}
	
	@Test
	public void printerShouldHaveReportEntriesAddedWhenPaymentIsMade(){
		Display display = mock(Display.class);
		Repository repository = mock(Repository.class);
		TaxApplier taxApplier = mock(TaxApplier.class);
		Printer printer = mock(Printer.class);
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(105);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_02));
		barcodeScanner.pay();
		
		verify(printer,times(2)).addEntry(any(ReportEntry.class));
	}
}
