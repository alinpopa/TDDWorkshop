package com.test.pos;

public class ReportEntry {
	private final String barcode;
	private final Amount price;
	private final Amount priceWithTax;

	public ReportEntry(final String barcode, final Amount price, final Amount priceWithTax) {
		this.barcode = barcode;
		this.price = price;
		this.priceWithTax = priceWithTax;
	}

	public String barcode() {
		return barcode;
	}

	public Amount price() {
		return price;
	}

	public Amount priceWithTax() {
		return priceWithTax;
	}

	@Override
	public String toString() {
		return "ReportEntry [barcode=" + barcode + ", price=" + price
				+ ", priceWithTax=" + priceWithTax + "]";
	}
}
