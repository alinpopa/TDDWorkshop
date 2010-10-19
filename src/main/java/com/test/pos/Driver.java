package com.test.pos;

public class Driver {
	public static void main(String[] args) {
		
		Catalog<Product> catalog = new ProductCatalog();
		catalog.set(new PstProduct("1207107", new Amount(1942), "Move Lamtech"));
		catalog.set(new PstProduct("147290", new Amount(190), "Faxzimfind"));
		catalog.set(new PstProduct("2721899", new Amount(980), "Ran Quaddom"));
		catalog.set(new PstFreeProduct("1808288", new Amount(9), "Ecolax"));
		catalog.set(new PstProduct("939702", new Amount(1009), "Roundzap"));
		catalog.set(new PstFreeProduct("1455503", new Amount(1298), "Voltphase"));
		
		POS pointOfSale = new PointOfSale(
				new PosDisplay(new ThirdPartyConsoleDevice()),
				new PosReportPrinter(),
				catalog,
				new BarcodeTaxApplier(new FederalTaxRate(8), new ProvincialTaxRate(5)));
		EventHandler<BarcodeEvent> eventHandler = new BarcodeScannerEventHandler(pointOfSale);
		
		eventHandler.handle(new BarcodeEvent("939702"));
		eventHandler.handle(new BarcodeEvent("1808288"));
		eventHandler.handle(new BarcodeEvent("1455503"));
		
	}
}
