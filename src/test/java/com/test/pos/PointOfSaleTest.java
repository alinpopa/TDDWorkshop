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
	private PointOfSale pointOfSale;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		printer = mock(Printer.class);
		display = mock(Display.class);
		repository = mock(Catalog.class);
		taxApplier = mock(TaxApplier.class);
		pointOfSale = new PointOfSale(display, printer, repository, taxApplier,
				null);
	}

	@Test
	public void shouldGenerateCashReportForScannedProductsWhenPaymentIsMade() {

		pointOfSale.pay();

		verify(printer).printCashReport();
	}

	@Test(expected = NoProductScanned.class)
	public void paymentShouldFailWhenNoProductScanned() {
		stub(printer.printCashReport()).toThrow(
				new EmptyReportEntriesException());

		pointOfSale.pay();
	}

	@Test
	public void shouldApplyTaxesToProductsWhenScanning() {
		PstFreeProduct product1 = new PstFreeProduct("product1",
				new Amount(100), "product description 1");
		stub(repository.get("product1")).toReturn(product1);

		pointOfSale.onBarcodeScan("product1");

		verify(taxApplier).calculateFor(product1);
	}

	@Test
	public void priceShouldBePrintedToDisplayWhenBarcodeIsScanned() {
		Product product = new PstFreeProduct("product1", new Amount(100),
				"some description");
		stub(repository.get("product1")).toReturn(product);

		pointOfSale.onBarcodeScan("product1");

		verify(display).printPrice(product.netPrice());
	}

	@Test
	public void errorShouldBeDisplayedWhenProductIsNotFoundInRepository() {
		stub(repository.get("product1")).toThrow(new InvalidProductException());

		pointOfSale.onBarcodeScan("product1");

		verify(display).printPriceNotFoundMessage(
				"No product found with barcode [product1]");
	}

	@Test
	public void errorMessageShouldBeDisplayedWhenProvidedBarcodeIsAnEmptyString() {
		pointOfSale.onBarcodeScan("");

		verify(display).printPriceNotFoundMessage(
				"No product found for empty barcode.");
	}

	@Test
	public void newEntryShouldBeAddedToThePrinterWhenBarcodeIsScanned() {
		stub(repository.get("product1")).toReturn(
				new PstFreeProduct("product1", new Amount(100),
						"product description 1"));
		pointOfSale.onBarcodeScan("product1");

		verify(printer, times(1)).addEntry(any(ReportEntry.class));
	}
}
