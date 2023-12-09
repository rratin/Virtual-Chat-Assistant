package com.chatassistant;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class movieServlet
 */
public class movieServlet extends HttpServlet {
	private String dbUrl = "jdbc:mysql://localhost:3306/register";
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movie = "No movie available today.";
	    try {
	        Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	        String sql = "SELECT movie FROM movies ORDER BY RAND() LIMIT 1";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            movie = rs.getString("movie");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.setContentType("text/plain"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(movie); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
