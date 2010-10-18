package com.test.pos;

public class BarcodeScannerEventHandler {

	private static final String PRODUCT_NOT_FOUND_MESSAGE = "No product found with barcode [%s]";

	private final Display display;
	private final Repository repository;
	private final TaxApplier taxApplier;
	
	public BarcodeScannerEventHandler(final Display display, final Repository repository, final TaxApplier taxApplier) {
		this.display = display;
		this.repository = repository;
		this.taxApplier = taxApplier;
	}

	public void handle(BarcodeEvent barcodeEvent) {
		if (barcodeEvent == null) {
			throw new InvalidBarCodeEventException();
		}
		displayBarCode(barcodeEvent.asString());
	}

	private void displayBarCode(final String barCodeEventValue) {
		try {
			int barCodePrice = taxApplier.apply(repository.get(barCodeEventValue));
			display.print(barCodePrice);
		} catch (InvalidBarCodeEventException e) {
			display.print(String.format(PRODUCT_NOT_FOUND_MESSAGE, barCodeEventValue));
		}
	}

	public void pay() {
		throw new UnsupportedOperationException();
	}
}
