package com.test.pos;

public interface Display {

	void print(Amount price);

	void print(String string);
	
	String message();
	
}
