package com.test.pos;

public class BarcodeScannerEventHandler {

	private static final String PRODUCT_NOT_FOUND_MESSAGE = "No product found with barcode [%s]";

	private final Display display;
	private final Repository repository;
	private final TaxApplier taxApplier;
	private final Printer printer;
	
	public BarcodeScannerEventHandler(final Display display, final Repository repository, final TaxApplier taxApplier, final Printer printer) {
		this.display = display;
		this.repository = repository;
		this.taxApplier = taxApplier;
		this.printer = printer;
	}

	public void handle(BarcodeEvent barcodeEvent) {
		if (barcodeEvent == null) {
			throw new InvalidBarCodeEventException();
		}
		displayBarCode(barcodeEvent.barcode());
	}

	private void displayBarCode(final String barCodeEventValue) {
		try {
			Amount simplePrice = repository.get(barCodeEventValue);
			Amount barCodeTaxesPrice = taxApplier.apply(simplePrice);
			display.print(simplePrice);
			addReportEntry(barCodeEventValue, simplePrice, barCodeTaxesPrice);
		} catch (InvalidBarCodeEventException e) {
			display.print(String.format(PRODUCT_NOT_FOUND_MESSAGE, barCodeEventValue));
		}
	}

	public void pay() {
		printer.printCashReport();
	}
	
	private void addReportEntry(final String barcode, final Amount price, final Amount priceWithTax){
		printer.addEntry(new ReportEntry(barcode, price, priceWithTax));
	}
}
