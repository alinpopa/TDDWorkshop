package com.test.pos;

import java.util.Collections;
import java.util.List;

public class Receipt {

	private final List<ReceiptEntry> receiptEntries;

	public Receipt(final List<ReceiptEntry> receiptEntries) {
		this.receiptEntries = receiptEntries;
	}

	public List<ReceiptEntry> entries() {
		return Collections.unmodifiableList(receiptEntries);
	}

}
