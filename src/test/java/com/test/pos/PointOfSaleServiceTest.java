package com.test.pos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class PointOfSaleServiceTest {

	private Printer printer;
	private Display display;
	private Catalog<Product> repository;
	private PointOfSaleService pointOfSale;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		printer = mock(Printer.class);
		display = mock(Display.class);
		repository = mock(Catalog.class);
		pointOfSale = new PointOfSaleService(display, printer, repository, null);
	}

	@Test
	public void priceShouldBePrintedToDisplayWhenBarcodeIsScanned() {
		Product product = new PstFreeProduct("product1", new Amount(100), "some description");
		stub(repository.get("product1")).toReturn(product);

		pointOfSale.onBarcodeScan("product1");

		verify(display).printPrice(product.netPrice());
	}

	@Test
	public void errorShouldBeDisplayedWhenProductIsNotFoundInRepository() {
		stub(repository.get("product1")).toThrow(new InvalidProductException());

		pointOfSale.onBarcodeScan("product1");

		verify(display).printPriceNotFoundMessage("No product found with barcode [product1]");
	}

	@Test
	public void errorMessageShouldBeDisplayedWhenProvidedBarcodeIsAnEmptyString() {
		pointOfSale.onBarcodeScan("");

		verify(display).printPriceNotFoundMessage("No product found for empty barcode.");
	}
}
