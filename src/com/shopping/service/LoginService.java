package com.shopping.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

	PreparedStatement statement = null;
	Connection connection = null;

	public boolean authenticate(String user, String pass) throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "admin");
			statement = connection.prepareStatement("SELECT * FROM credentials WHERE name = ? and password = ?");
			statement.setString(1, user);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
			String username = rs.getString("name");
			String password = rs.getString("password");
			
			if (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)) {
				return true;
			}
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finally {

			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		
		
		
		return false;

	}

}
