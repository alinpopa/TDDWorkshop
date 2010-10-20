package com.test.pos;

public interface PointOfSale {
	void onBarcodeScan(String barcode);

	void onCompleteSale();
}
