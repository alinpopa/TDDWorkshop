package com.test.pos;

public class PointOfSale implements POS{

	private static final String PRODUCT_NOT_FOUND_MESSAGE = "No product found with barcode [%s]";
	private static final String EMPTY_BARCODE_NOT_FOUND_MESSAGE = "No product found for empty barcode.";
	
	private final Display display;
	private final Printer printer;
	private final Catalog<Product> catalog;
	private final TaxApplier taxApplier;
	
	public PointOfSale(final Display display, final Printer printer, final Catalog<Product> catalog, final TaxApplier taxApplier) {
		this.display = display;
		this.printer = printer;
		this.catalog = catalog;
		this.taxApplier = taxApplier;
	}

	@Override
	public void pay() {
		try{
			printer.printCashReport();
		}catch(EmptyReportEntriesException e){
			throw new NoProductScanned();
		}
	}

	@Override
	public void onBarcodeScan(final String barcode) {
		if(isValidBarcode(barcode)){
			handleScanOfValidBarcode(barcode);
		}else{
			handleScanOfInvalidBarcode(barcode);
		}
	}

	private void handleScanOfInvalidBarcode(final String barcode){
		displayPriceNotFound(EMPTY_BARCODE_NOT_FOUND_MESSAGE);
	}
	
	private void handleScanOfValidBarcode(final String barcode){
		try{
			Product catalogProduct = catalog.get(barcode);
			displayPrice(catalogProduct);
			addEntryToPrinter(catalogProduct);
		}catch(InvalidProductException e){
			displayPriceNotFound(String.format(PRODUCT_NOT_FOUND_MESSAGE, barcode));
		}
	}
	
	private void displayPrice(final Product product){
		display.printPrice(product.netPrice());
	}
	
	private void addEntryToPrinter(final Product product) {
		printer.addEntry(new ReportEntry(product.barcode(), product.netPrice(), product.apply(taxApplier)));
	}

	private void displayPriceNotFound(final String message) {
		display.printPriceNotFoundMessage(message);
	}

	private boolean isValidBarcode(final String barcode){
		if(barcode.length() == 0){
			return false;
		}
		return true;
	}
}
