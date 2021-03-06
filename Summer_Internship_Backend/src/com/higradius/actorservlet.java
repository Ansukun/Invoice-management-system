package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class actorservlet
 */

public class actorservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url="jdbc:mysql://localhost:3306/data";
	private static String username="root";
	private static String password="Plgstation5853";
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<HashMap<String,Object>> result= new ArrayList<HashMap<String,Object>>();
		try {
			Integer start=Integer.parseInt(request.getParameter("start"));
			Integer limit=Integer.parseInt(request.getParameter("limit"));
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement("SELECT name_customer,cust_number,invoice_id,total_open_amount,due_in_date,clear_date,Delay_Grouped FROM mytable LIMIT ?,?;");
			ps.setInt(1, start);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				HashMap<String,Object> row=new HashMap<String, Object>();
				row.put("name", rs.getString(1));
				row.put("cust_number", rs.getString(2));
				row.put("invoice_id", rs.getString(3));
				row.put("total_open_amount", rs.getFloat(4));
				row.put("due_in_date", rs.getString(5));
				row.put("clear_date", rs.getString(6));
				row.put("Delay_Grouped", rs.getString(7));
				result.add(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson=new Gson();
		response.getWriter().print(gson.toJson(result));
	}

}