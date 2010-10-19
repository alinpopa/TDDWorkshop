package com.test.pos;

public class PstProduct implements Product {
	private final String barcode;
	private final Amount netPrice;
	private final String description;
	
	public PstProduct(final String barcode, final Amount netPrice, final String descrition){
		this.barcode = barcode;
		this.netPrice = netPrice;
		this.description = descrition;
	}
	
	@Override
	public String barcode() {
		return barcode;
	}

	@Override
	public Amount netPrice() {
		return netPrice;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public Amount apply(TaxApplier taxApplier) {
		return taxApplier.calculateFor(this);
	}

}
