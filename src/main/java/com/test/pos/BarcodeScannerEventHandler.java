package com.test.pos;


public class BarcodeScannerEventHandler implements EventHandler<BarcodeEvent>{

	

	private final POS pointOfSale;
	
	public BarcodeScannerEventHandler(final POS pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	@Override
	public void handle(BarcodeEvent barcodeEvent) {
		if (barcodeEvent == null) {
			throw new InvalidBarCodeEventException();
		}
		displayBarCode(barcodeEvent);
	}

	private void displayBarCode(final BarcodeEvent barcodeEvent) {
		pointOfSale.onBarcodeScan(barcodeEvent.barcode());
	}
}
