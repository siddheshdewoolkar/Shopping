package com.shopping.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Item;

public class ItemService {

	PreparedStatement statement = null;
	Connection connection = null;
	
	public List<Item> getItems() throws SQLException, ClassNotFoundException {
		
		List<Item> items = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "admin");
			statement = connection.prepareStatement("SELECT * FROM items");
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {

				Item item;
				int item_id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				item = new Item(item_id, name, price,0);
				items.add(item);
			}
		
	}
		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	
		return items;
	
}
}
