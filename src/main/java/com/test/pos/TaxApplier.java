package com.test.pos;

public interface TaxApplier {
	Amount calculateFor(PstFreeProduct product);
	
	Amount calculateFor(PstProduct product);
}
