package com.test.pos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PosReportPrinter implements Printer {

	private final Map<String, ReportEntry> reportEntries;
	
	public PosReportPrinter(){
		this.reportEntries = new LinkedHashMap<String, ReportEntry>();
	}
	
	@Override
	public String printCashReport() {
		StringBuilder text = new StringBuilder();
		for(Entry<String, ReportEntry> entry : reportEntries.entrySet()){
			ReportEntry reportEntry = entry.getValue();
			text.append("[");
			text.append(reportEntry.barcode());
			text.append(":");
			text.append(reportEntry.price());
			text.append(":");
			text.append(reportEntry.priceWithTax());
			text.append("]");
		}
		return text.toString();
	}

	@Override
	public void addEntry(ReportEntry reportEntry) {
		reportEntries.put(reportEntry.barcode(), reportEntry);
	}

}
