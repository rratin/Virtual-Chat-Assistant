package com.chatassistantTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.chatassistant.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoginServletTest {

    private LoginServlet loginServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private LoginDao loginDao;

    @BeforeEach
    void setUp() {
        loginServlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        loginDao = mock(LoginDao.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoPostSuccessfulLogin() throws ServletException, IOException {
        String testEmail = "test@example.com";
        String testPassword = "password";
        String testFullname = "Test User"; 
        User mockUser = new User();
        mockUser.setEmail(testEmail);
        mockUser.setPassword(testPassword);
        mockUser.setFullname(testFullname); 
        mockUser.setIsActive(true);

        when(request.getParameter("email")).thenReturn(testEmail);
        when(request.getParameter("password")).thenReturn(testPassword);
        when(loginDao.getUserByEmail(testEmail)).thenReturn(mockUser);

        loginServlet.doPost(request, response);

        verify(session).setAttribute(eq("user"), argThat(user -> testEmail.equals(((User) user).getEmail()) && testPassword.equals(((User) user).getPassword())));
        verify(session).setAttribute("fullname", testFullname); 
        verify(session).setAttribute("email", mockUser.getEmail());
        verify(response).sendRedirect("DailyTipServlet");
    }


    @Test
    void testDoPostFailedLogin() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("wrong@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        when(loginDao.getUserByEmail("wrong@example.com")).thenReturn(null);

        loginServlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp?error=true");
    }
}
