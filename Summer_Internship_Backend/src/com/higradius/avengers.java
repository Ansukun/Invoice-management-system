package com.higradius;
import com.google.gson.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * Servlet implementation class avengers
 */
@WebServlet("/avengers")
public class avengers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url = "jdbc:mysql://127.0.0.1:3306/sakila";
	private static String username = "root";
	private static String pass =  "Plgstation5853";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public avengers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          List<HashMap<String,Object>>result = new ArrayList<HashMap<String,Object>>();
          try
          {
        	  Integer start = Integer.parseInt(request.getParameter("start"));
        	  Integer limit = Integer.parseInt(request.getParameter("limit"));
        	  DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        	  Connection con = DriverManager.getConnection(url, username , pass);
        	  PreparedStatement ps = con.prepareStatement("select actor_id,first_name,last_name from actor LIMIT ? , ?");
        	  ps.setInt(1, start);
        	  ps.setInt(2, limit);
        	  ResultSet rs = ps.executeQuery();
        	  while(rs.next())
        	  {
        		  HashMap<String,Object> row = new HashMap<String,Object>();
        		  row.put("actor_id",rs.getInt(1) );
        		  row.put("first_name", rs.getString(2));
        		  row.put("last_name", rs.getString(3));
        		  result.add(row);
        		  
        	  }
          }
          catch(Exception e)
          {
        	  e.printStackTrace();
          }
          Gson gson = new Gson();
          gson.toJson(result);
          response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

}
