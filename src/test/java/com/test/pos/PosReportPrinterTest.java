package com.test.pos;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class PosReportPrinterTest {
	@Test
	public void shouldDisplayAllCashReportEntries(){
		PosReportPrinter posReportPrinter = new PosReportPrinter();
		posReportPrinter.addEntry(new ReportEntry("code1", new Amount(100), new Amount(105)));
		posReportPrinter.addEntry(new ReportEntry("code2", new Amount(200), new Amount(208)));
		posReportPrinter.addEntry(new ReportEntry("code3", new Amount(300), new Amount(306)));
		
		String report = posReportPrinter.printCashReport();
		
		assertThat(report, is("[code1:100:105][code2:200:208][code3:300:306]"));
	}
}
