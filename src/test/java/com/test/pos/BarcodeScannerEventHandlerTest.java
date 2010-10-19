package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import org.junit.Before;
import org.junit.Test;

public class BarcodeScannerEventHandlerTest {
	
	private static final String PRODUCT_01 = "product01";
	private static final String PRODUCT_02 = "product02";
	private static final String INVALID_BARCODE = "invalid barcode";

	private Display display;
	private Repository repository;
	private TaxApplier taxApplier;
	private Printer printer;
	
	@Before
	public void setUp(){
		display = mock(Display.class);
		repository = mock(Repository.class);
		taxApplier = mock(TaxApplier.class);
		printer = mock(Printer.class);
	}
	
	@Test
	public void shouldDisplayErrorOnDisplayWhenWrongBarCodeIsPassedIn(){
		stub(repository.get(INVALID_BARCODE)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(INVALID_BARCODE));
		
		verify(display).printPriceNotFoundMessage("No product found with barcode ["+INVALID_BARCODE+"]");
	}


	@Test(expected = InvalidBarCodeEventException.class)
	public void shouldNotHandleNullBarCodeEvents(){
		stub(repository.get(null)).toThrow(new InvalidBarCodeEventException());
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(null);
	}
	
	@Test
	public void shouldDisplayPriceForTheGivenBarCode(){
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).printPrice(price);
	}
	
	@Test
	public void displayedPriceShouldNotHaveTaxesApplied(){
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(display).printPrice(priceWithTax);
	}
	
	@Test
	public void shouldApplyTaxesToTheGivenPrice(){
		Amount price = new Amount(100);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		
		verify(taxApplier).apply(price);
	}
	
	@Test
	public void shouldGenerateCashReportForScannedProductsWhenPaymentIsMade(){
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(105);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		barcodeScanner.pay();
		
		verify(printer).printCashReport();
	}
	
	@Test
	public void printerShouldHaveReportEntriesAddedWhenPaymentIsMade(){
		Amount price = new Amount(100);
		Amount priceWithTax = new Amount(105);
		stub(repository.get(PRODUCT_01)).toReturn(price);
		stub(taxApplier.apply(price)).toReturn(priceWithTax);
		BarcodeScannerEventHandler barcodeScanner = getBarcodeScannerEventHandler();
		
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_01));
		barcodeScanner.handle(new BarcodeEvent(PRODUCT_02));
		barcodeScanner.pay();
		
		verify(printer,times(2)).addEntry(any(ReportEntry.class));
	}
	
	private BarcodeScannerEventHandler getBarcodeScannerEventHandler() {
		return new BarcodeScannerEventHandler(display, repository, taxApplier, printer);
	}
}
