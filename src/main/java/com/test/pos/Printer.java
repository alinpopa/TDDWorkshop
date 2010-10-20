package com.test.pos;

import java.util.List;

public interface Printer {

	String printCashReport();

	void addEntry(ReportEntry reportEntry);

	List<ReportEntry> reportEntries();
}
