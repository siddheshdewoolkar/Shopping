package com.shopping.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Item;

public class CartService {
	
	private List<Item> itemList;
	private Connection conn;
	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Item> fetchAllItems() {
		
		itemList = new ArrayList<>();
		
		connectToDatabase();
		
		try {
			String query = ("SELECT * FROM items");
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int price = rs.getInt(3);
					
					
					Item item = new Item(id, name, price,0);
					itemList.add(item);
				}
			}
			
			return itemList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return itemList;
	}

	private void connectToDatabase() {
		conn = null;
		String connectionUrl = "jdbc:mysql://localhost:3306/shopping";
		try {
			// Establish the connection.

			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(connectionUrl, "root", "admin");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
