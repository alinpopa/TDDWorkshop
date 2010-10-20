package com.test.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SaleServiceTest {

	@Test(expected = NoProductScannedException.class)
	public void shouldFailWhenNoReceiptEntries() throws Exception {
		final SaleService saleService = new SaleService();
		
		saleService.tallyReceipt();
	}
	
	@Test
	public void generatedReceiptShouldContainValidEntries(){
		final SaleService saleService = new SaleService();
		saleService.add(new PstFreeProduct("barcode1", new Amount(34), "description1"));
		saleService.add(new PstFreeProduct("barcode2", new Amount(35), "description2"));
		
		Receipt receipt = saleService.tallyReceipt();
		
		assertThat(receipt.entries().size(), is(2));
	}
}
