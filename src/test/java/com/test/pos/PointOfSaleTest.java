package com.test.pos;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class PointOfSaleTest {

	private Printer printer;
	private Display display;
	private Catalog<Product> repository;
	private TaxApplier taxApplier;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp(){
		printer = mock(Printer.class);
		display = mock(Display.class);
		repository = mock(Catalog.class);
		taxApplier = mock(TaxApplier.class);
	}
	
	@Test
	public void shouldGenerateCashReportForScannedProductsWhenPaymentIsMade() {
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
		
		pointOfSale.pay();

		verify(printer).printCashReport();
	}

	@Test
	public void shouldApplyTaxesToProductsWhenScanning(){
		PstFreeProduct product1 = new PstFreeProduct("product1", new Amount(100), "product description 1");
		stub(repository.get("product1")).toReturn(product1);
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
		
		pointOfSale.onBarcodeScan("product1");
		
		verify(taxApplier).calculateFor(product1);
	}
	
	@Test
	public void priceShouldBePrintedToDisplayWhenBarcodeIsScanned() {
		Product product = new PstFreeProduct("product1", new Amount(100), "some description");
		stub(repository.get("product1")).toReturn(product);
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
		
		pointOfSale.onBarcodeScan("product1");

		verify(display).printPrice(product.netPrice());
	}

	@Test
	public void errorShouldBeDisplayedWhenProductIsNotFoundInRepository(){
		stub(repository.get("product1")).toThrow(new InvalidProductException());
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
	
		pointOfSale.onBarcodeScan("product1");

		verify(display).printPriceNotFoundMessage("No product found with barcode [product1]");
	}
	
	@Test
	public void errorMessageShouldBeDisplayedWhenProvidedBarcodeIsAnEmptyString(){
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
		
		pointOfSale.onBarcodeScan("");

		verify(display).printPriceNotFoundMessage("No product found for empty barcode.");
	}
	
	@Test
	public void newEntryShouldBeAddedToThePrinterWhenBarcodeIsScanned(){
		stub(repository.get("product1")).toReturn(new PstFreeProduct("product1", new Amount(100), "product description 1"));
		PointOfSale pointOfSale = new PointOfSale(display, printer, repository, taxApplier);
		
		pointOfSale.onBarcodeScan("product1");

		verify(printer,times(1)).addEntry(any(ReportEntry.class));
	}
}
