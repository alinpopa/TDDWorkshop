package com.test.pos;

// SMELL Misnamed class. This is not the barcode scanner;
// it is a class that receives barcode scanning events.
// The first event is "barcode" which contains a string.
public class CodeBarScanner {

	private final Display display;

	public CodeBarScanner(final Display display){
		this.display = display;
	}

  // SMELL Duplication between the two bodies of
  // the if statements here.
	public void scan(String barCode) {
		if(barCode == null){
			display.print("some error");
		}else if("invalid barcode".equals(barCode)){
			display.print("some error");
		}
    // SMELL Duplication between this 100L and the
    // 100L in the test.
		display.print(100L);
	}

}
