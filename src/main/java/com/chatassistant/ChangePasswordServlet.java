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
import java.sql.ResultSet;

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private String dbUrl = "jdbc:mysql://localhost:3306/register";
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
    private String dbDriver = "com.mysql.jdbc.Driver";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute("error", "New password and confirmation do not match.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response); 
            return;
        }


        try {
            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String sql = "SELECT password FROM user WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getString("password").equals(currentPassword)) {
                sql = "UPDATE user SET password = ? WHERE email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, newPassword);
                ps.setString(2, email);
                int result = ps.executeUpdate();

                if (result > 0) {
                    request.setAttribute("message", "Password updated successfully.");
                    request.getRequestDispatcher("home.jsp").forward(request, response); 
                } else {
                  
                    request.setAttribute("error", "Password update failed.");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                }

            } else {
                
                request.setAttribute("error", "Incorrect current password.");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
