package com.test.pos;

public interface Product {
	String barcode();
	
	Amount netPrice();
	
	String description();
	
	Amount apply(TaxApplier taxApplier);
}
