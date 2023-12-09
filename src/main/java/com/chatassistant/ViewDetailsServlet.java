package com.chatassistant;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class ViewDetailsServlet
 */
public class ViewDetailsServlet extends HttpServlet {
	private ConnectionFactory connectionFactory = new ConnectionFactory();

    // Allow setting a different ConnectionFactory (for testing)
    public void setConnectionFactory(ConnectionFactory factory) {
        this.connectionFactory = factory;
    }

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");
        User user = (User) session.getAttribute("user");

        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = connectionFactory.getConnection();
            String sql = "SELECT user FROM notes WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userEmail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                
                
                user.setEmail(email);
                user.setPassword(password);
                
                user.setFullname(fullname);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("user", user); 
        request.getRequestDispatcher("viewDetails.jsp").forward(request, response);


	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
