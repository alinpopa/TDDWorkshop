package com.test.pos;

public class PointOfSaleService implements PointOfSale {

	private static final String PRODUCT_NOT_FOUND_MESSAGE = "No product found with barcode [%s]";
	private static final String EMPTY_BARCODE_NOT_FOUND_MESSAGE = "No product found for empty barcode.";

	private final Display display;
	private final Printer printer;
	private final Catalog<Product> catalog;
	private final Sale sale;

	public PointOfSaleService(final Display display, final Printer printer,
			final Catalog<Product> catalog, final Sale sale) {
		this.display = display;
		this.printer = printer;
		this.catalog = catalog;
		this.sale = sale;
	}

	@Override
	public void onBarcodeScan(final String barcode) {
		if (isValidBarcode(barcode)) {
			handleScanOfValidBarcode(barcode);
		} else {
			handleScanOfInvalidBarcode(barcode);
		}
	}

	private void handleScanOfInvalidBarcode(final String barcode) {
		displayPriceNotFound(EMPTY_BARCODE_NOT_FOUND_MESSAGE);
	}

	private void handleScanOfValidBarcode(final String barcode) {
		try {
			Product catalogProduct = catalog.get(barcode);
			displayPrice(catalogProduct);
		} catch (InvalidProductException e) {
			displayPriceNotFound(String.format(PRODUCT_NOT_FOUND_MESSAGE,
					barcode));
		}
	}

	private void displayPrice(final Product product) {
		display.printPrice(product.netPrice());
	}

	private void displayPriceNotFound(final String message) {
		display.printPriceNotFoundMessage(message);
	}

	private boolean isValidBarcode(final String barcode) {
		if (barcode.length() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void onCompleteSale() {
		printer.printReceipt(sale.tallyReceipt());
	}
}
