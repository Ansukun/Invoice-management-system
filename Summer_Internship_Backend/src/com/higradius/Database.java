package com.higradius;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

// This class can be used to initialize the database connection 
public class Database { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		// Initialize all the information regarding 
		// Database Connection 
		String dbDriver = "com.mysql.cj.jdbc.Driver"; 
		String dbURL = "jdbc:mysql:// localhost:8082/"; 
		// Database name to access 
		String dbName = "h2hbabba 1533"; 
		String dbUsername = "root"; 
		String dbPassword = "Plgstation5853"; 

		Class.forName(dbDriver); 
		Connection con = DriverManager.getConnection(dbURL + dbName, 
													dbUsername, 
													dbPassword); 
		if (con != null)
		{
			System.out.println("Database Connected successfully"); 
		}
		else
		{
			System.out.println("Database not Connected successfully"); 

		}
		return con;
	} 
} 
