package com.test.pos;

public interface POS {
	void pay();
	
	void onBarcodeScan(String barcode);
}
