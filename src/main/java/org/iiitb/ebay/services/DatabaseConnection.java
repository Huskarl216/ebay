package org.iiitb.ebay.services;

import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseConnection {
	Statement statement;
	ResultSet resultSet;
	Connection connection = null;
	String query = null;

	// Constructor for opening the Database Connection

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Found");
		}

		catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found: " + e);
		}

		String url = "jdbc:mysql://localhost/ebay_mock";
		// String url = "jdbc:mysql://192.168.30.11:3306/sdc";

		String user = "root";
		String password = "1234";
		connection = null;

		try {
			connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Successfully Connected to Database");
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e);
		}

	}

	public String authenticateUser(String user_name) throws JSONException {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		try {
			query = "select * from users where user_name=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.put("result", "success");
				user.put("user_id", resultSet.getInt("user_id"));
				user.put("user_name", resultSet.getString("user_name"));
				user.put("dob", resultSet.getString("dob"));
				user.put("pic_location", resultSet.getString("pic_location"));
			}
			else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user.toString();

	}
	
	public String getItemDetails(int itemid) throws JSONException {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject item = new JSONObject();
		try {
			query = "select * from items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemid);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				item.put("result", "success");
				item.put("item_id", resultSet.getInt("item_id"));
				item.put("item_name", resultSet.getString("item_name"));
				item.put("expiry_date", resultSet.getString("expiry_date"));
				item.put("price", resultSet.getInt("price"));
				item.put("pic_location", resultSet.getString("pic_location"));
			}
			else {
				item.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item.toString();

	}
}
