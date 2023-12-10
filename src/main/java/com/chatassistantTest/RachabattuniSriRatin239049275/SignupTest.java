package com.chatassistantTest;

import static org.junit.jupiter.api.Assertions.*;
import com.chatassistant.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SignupTest {

    private Signup signupServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        signupServlet = new Signup();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);

        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("fullname")).thenReturn("Test User");
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void testDoPostSuccessfulSignup() throws ServletException, IOException {

        signupServlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp");
    }

   
}
