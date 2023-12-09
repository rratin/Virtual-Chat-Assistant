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
import java.time.LocalDateTime;

/**
 * Servlet implementation class SetReminderServlet
 */
public class SetReminderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;

    // Default constructor for production use
    public SetReminderServlet() throws Exception {
        this(DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "ratin1026"));
    }

   
    public SetReminderServlet(Connection connection) {
        this.connection = connection;
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    
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
        String userEmail = (String) session.getAttribute("email");
        String message = request.getParameter("message");
        LocalDateTime reminderTime = LocalDateTime.parse(request.getParameter("reminderTime"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
           
            String sql = "INSERT INTO reminders (user_email, message, reminder_time) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userEmail);
            ps.setString(2, message);
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(reminderTime));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("home.jsp");
    
	}

}
