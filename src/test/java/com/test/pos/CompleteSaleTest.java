package com.test.pos;

import java.util.ArrayList;

import org.junit.*;
import org.mockito.Mockito;

public class CompleteSaleTest {

	@Test
	public void zeroItems() throws Exception {
		Printer printer = Mockito.mock(Printer.class);
		Display display = Mockito.mock(Display.class);
		Sale sale = Mockito.mock(Sale.class);
		Receipt receipt = new Receipt(new ArrayList<ReceiptEntry>());
		Mockito.stub(sale.tallyReceipt()).toReturn(receipt);
		@SuppressWarnings("unchecked")
		Catalog<Product> productCatalog = Mockito.mock(Catalog.class);
		PointOfSaleService pointOfSale = new PointOfSaleService(display, printer, productCatalog, sale);

		pointOfSale.onCompleteSale();

		Mockito.verify(printer).printReceipt(receipt);
	}
}
