package com.chatassistant;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private ConnectionFactory connectionFactory;

    // Constructor that accepts a ConnectionFactory
    public EditProfileServlet(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
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
		String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setFullname(fullname);
        user.setEmail(email);

        ProfileDao profileDao = new ProfileDao();
        boolean result = profileDao.updateProfile(user);

        if (result) {
            session.setAttribute("fullname", fullname);
            session.setAttribute("email", email);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("editProfile.jsp?error=true");
        }
	}

	

}
