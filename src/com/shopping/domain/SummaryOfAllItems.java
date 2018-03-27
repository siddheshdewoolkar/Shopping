package com.shopping.domain;

import java.util.List;

public class SummaryOfAllItems {

	private List<Summary> summary;
	private int grandTotal;

	public List<Summary> getSummary() {
		return summary;
	}

	public void setSummary(List<Summary> summary) {
		this.summary = summary;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
