package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editData
 */
@WebServlet("/editData")
public class editData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url="jdbc:mysql://localhost:3306/data";
	private static String username="root";
	private static String password="Plgstation5853";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editData() {
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
			String invoice_id= request.getParameter("checkedId");
			Double invoice_amount=Double.parseDouble(request.getParameter("invoiceAmount"));
			String note=request.getParameter("invoiceNotes");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			
			PreparedStatement ps=con.prepareStatement("UPDATE mytable SET total_open_amount = ?,Delay_Grouped = ? WHERE invoice_id = ?;");
			ps.setDouble(1,invoice_amount);
			ps.setString(2, note);
			ps.setString(3, invoice_id);
			ps.executeUpdate();
			
			System.out.println(invoice_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8020/H2HBABBA1533/");
	}

}
