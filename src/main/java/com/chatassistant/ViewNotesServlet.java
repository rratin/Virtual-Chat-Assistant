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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class ViewNotesServlet
 */
public class ViewNotesServlet extends HttpServlet {
	private String dbUrl = "jdbc:mysql://localhost:3306/register";
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewNotesServlet() {
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
        ArrayList<String> notes = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String sql = "SELECT note_text FROM notes WHERE user_email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userEmail);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notes.add(rs.getString("note_text"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("error", "Database error occurred");
            request.getRequestDispatcher("error_form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("notes", notes);
        request.getRequestDispatcher("viewNotes.jsp").forward(request, response);
    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	

}
