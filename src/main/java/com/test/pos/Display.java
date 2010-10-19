package com.test.pos;

public interface Display {

	void printPrice(Amount price);
	
	void printPriceNotFoundMessage(String message);	
}
