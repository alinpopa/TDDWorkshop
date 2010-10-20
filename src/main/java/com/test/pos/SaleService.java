package com.test.pos;

import java.util.ArrayList;
import java.util.List;

public class SaleService implements Sale {
	
	private final List<Product> saleProducts;
	
	public SaleService() {
		this.saleProducts = new ArrayList<Product>();
	}

	@Override
	public Receipt tallyReceipt() {
		if(!isReadyForTally()){
			throw new NoProductScannedException();
		}
		return new Receipt(convertToReceiptEntries());
	}

	public void add(final Product product) {
		saleProducts.add(product);
	}

	private List<ReceiptEntry> convertToReceiptEntries(){
		List<ReceiptEntry> receiptEntries = new ArrayList<ReceiptEntry>();
		for(Product product : saleProducts){
			receiptEntries.add(new ReceiptEntry());
		}
		return receiptEntries;
	}
	
	private boolean isReadyForTally(){
		if(saleProducts.isEmpty()){
			return false;
		}
		return true;
	}
}
