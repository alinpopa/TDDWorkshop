package com.test.pos;

public class Driver {
	public static void main(String[] args) {
		BarcodeScannerEventHandler barcodeScannerEventHandler = new BarcodeScannerEventHandler(
				new Repository() {
					@Override
					public Amount get(String barCode) {
						return new Amount(2000);
					}
				},
				new BarcodeTaxApplier(new FederalTaxRate(5),
						new ProvincialTaxRate(8)), new PosDisplay(new ThirdPartyConsoleDevice()), new PosReportPrinter());

		barcodeScannerEventHandler.handle(new BarcodeEvent("123"));
		barcodeScannerEventHandler.handle(null);
	}
}
