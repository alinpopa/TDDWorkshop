package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReceiptTest {
	
	@Test
	public void entriesPassedToTheConstructorShouldBeAvailable() throws Exception {
		List<ReceiptEntry> receiptEntries = new ArrayList<ReceiptEntry>();
		receiptEntries.add(new ReceiptEntry());
		Receipt receipt = new Receipt(receiptEntries);
		
		List<ReceiptEntry> actualReceiptEntries = receipt.entries();
		
		assertThat(actualReceiptEntries.size(), is(1));
	}
}
