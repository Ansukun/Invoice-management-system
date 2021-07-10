package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet("/addData")
public class addData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url="jdbc:mysql://localhost:3306/data";
	private static String username="root";
	private static String password="Plgstation5853";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		try {
			
			String name=request.getParameter("name");
			String cust_no=request.getParameter("cust_no");
			String invoice_id= request.getParameter("invoice_no");
			Double invoice_amount=Double.parseDouble(request.getParameter("invoice_amt"));
			String date=request.getParameter("due_date");
			String note=request.getParameter("notes");
     		Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement("INSERT INTO  mytable(cust_number,name_customer,due_in_date,total_open_amount,invoice_id,Notes) VALUES (?,?,?,?,?,?);");
			ps.setString(1, cust_no);
			ps.setString(2, name);
			ps.setString(3, date);
			ps.setDouble(4, invoice_amount);
			ps.setString(5, invoice_id);
			ps.setString(6, note);
			ps.executeUpdate();
			
			System.out.println(name+" "+cust_no+" "+invoice_id+" " +invoice_amount+" " +date+ " "+note);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8020/H2HBABBA1533/");
		
	}

}
