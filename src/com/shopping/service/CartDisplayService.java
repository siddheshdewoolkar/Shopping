package com.shopping.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Item;

public class CartDisplayService {

	PreparedStatement statement = null;
	Connection connection = null;
	
	
	public List<Item> cartDisplay(String[] selectedItems, Integer quantity) {
		List<Item> cart = new ArrayList<>();
		
		if(selectedItems != null && selectedItems.length > 0) {
		System.out.println(selectedItems.length);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "admin");
			
			for(String selectedItem:selectedItems){
				
				int id=Integer.parseInt(selectedItem);
				
				statement = connection.prepareStatement("SELECT name, price FROM items WHERE id = ?");
				statement.setString(1, selectedItem);
				
				ResultSet rs = statement.executeQuery();
				
				while (rs.next()) {
					Item item;
					String name = rs.getString("name");
					int price = rs.getInt("price");
					item = new Item(id, name, price, quantity);
					cart.add(item);
				}
			}
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cart;
	}
		return null;
	}
	
}
