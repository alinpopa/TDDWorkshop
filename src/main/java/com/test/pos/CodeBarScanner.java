package com.test.pos;

public class CodeBarScanner {

	private final Display display;
	
	public CodeBarScanner(final Display display){
		this.display = display;
	}
	
	public void scan(String barCode) {
		if(barCode == null){
			display.print("some error");
		}else if("invalid barcode".equals(barCode)){
			display.print("some error");
		}
		display.print(100L);
	}

}
