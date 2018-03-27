package com.shopping.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shopping.domain.Item;

public class CheckoutService {
	
	private Map<Integer, Integer> mapIdQuantity;
	private List<Item> itemList;
	private Map<Item, Integer> mapItemPrice;

	public Map<Integer, Integer> getMapIdQuantity() {
		return mapIdQuantity;
	}

	public Map<Item, Integer> getMapItemPrice() {
		return mapItemPrice;
	}

	public void setMapItemPrice(Map<Item, Integer> mapItemPrice) {
		this.mapItemPrice = mapItemPrice;
	}

	public void setMapIdQuantity(Map<Integer, Integer> mapIdQuantity) {
		this.mapIdQuantity = mapIdQuantity;
		makeItemPriceMap();
	}
	
	public void makeItemPriceMap(){
		
		mapItemPrice = new HashMap<Item, Integer>();
		
		Iterator<Integer> iterator = mapIdQuantity.keySet().iterator();
		
		while(iterator.hasNext()) {
			Integer key = iterator.next();
			int id = key.intValue();
			Item thisItem = null;
			for (Item item : itemList) {
				if(item.getId() == id) {
					thisItem = item;
					break;
				}
			}
			Integer price = mapIdQuantity.get(key).intValue() * thisItem.getPrice();
			mapItemPrice.put(thisItem, price);
			
		}	
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public int getTotalPrice() {
		
		Iterator<Item> iterator = mapItemPrice.keySet().iterator();
		int price = 0;
		while(iterator.hasNext()) {
			Item item = iterator.next();
			
			price += mapItemPrice.get(item);
			
		}
		
		return price;
		
		
	}

}
