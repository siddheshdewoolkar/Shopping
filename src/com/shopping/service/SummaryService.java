package com.shopping.service;

import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Item;
import com.shopping.domain.Summary;
import com.shopping.domain.SummaryOfAllItems;

public class SummaryService {

	public SummaryOfAllItems getSummary(List<Item> cart){
		
		SummaryOfAllItems summaryOfAllItems=new SummaryOfAllItems();
		List<Summary> lstSummary=new ArrayList<>();
		summaryOfAllItems.setSummary(lstSummary);
		int grandTotal=0;
		
		for(Item item:cart){
			String name=item.getName();
			int quantity=item.getQuantity();
			int price=item.getPrice();
			int totalPrice=quantity*price;
			grandTotal+=totalPrice;
			Summary summary=new Summary(name,quantity,totalPrice,price);
			lstSummary.add(summary);
		}
		summaryOfAllItems.setGrandTotal(grandTotal);
		return summaryOfAllItems;
		
	}
	
	
	
}
