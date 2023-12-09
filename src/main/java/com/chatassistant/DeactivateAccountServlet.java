package com.chatassistant;


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

/**
 * Servlet implementation class DeactivateAccountServlet
 */
public class DeactivateAccountServlet extends HttpServlet {
	private ConnectionFactory connectionFactory;

    // Constructor that accepts a ConnectionFactory
    public DeactivateAccountServlet(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeactivateAccountServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email"); // Assuming email is stored in the session

        try {
            Connection con = connectionFactory.getConnection();
            String sql = "UPDATE user SET isActive = 0 WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            int result = ps.executeUpdate();
            if (result > 0) {
                
                session.invalidate(); 
                response.sendRedirect("login.jsp"); 
            } else {
              
                request.setAttribute("error", "An error occurred. Account deactivation was not successful.");
                request.getRequestDispatcher("viewDetails.jsp").forward(request, response); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

}
