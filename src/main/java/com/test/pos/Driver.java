package com.test.pos;

import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {

		Catalog<Product> catalog = new ProductCatalog();
		catalog.set(new PstProduct("1207107", new Amount(1942), "Move Lamtech"));
		catalog.set(new PstProduct("147290", new Amount(190), "Faxzimfind"));
		catalog.set(new PstProduct("2721899", new Amount(980), "Ran Quaddom"));
		catalog.set(new PstFreeProduct("1808288", new Amount(9), "Ecolax"));
		catalog.set(new PstProduct("939702", new Amount(1009), "Roundzap"));
		catalog.set(new PstFreeProduct("1455503", new Amount(1298), "Voltphase"));

		PointOfSale pointOfSale = new PointOfSaleService(
				new PosDisplay(new ThirdPartyConsoleDevice()),
				new Printer() {
					
					@Override
					public void printReceipt(Receipt receipt) {
						throw new UnsupportedOperationException();
					}
				},
				catalog,
				new Sale() {
					@Override
					public Receipt tallyReceipt() {
						return new Receipt(new ArrayList<ReceiptEntry>());
					}
				});
		EventHandler<BarcodeEvent> eventHandler = new BarcodeScannerEventHandler(
				pointOfSale);

		eventHandler.handle(new BarcodeEvent("939702"));
		eventHandler.handle(new BarcodeEvent("1808288"));
		eventHandler.handle(new BarcodeEvent("1455503"));
	}
}
