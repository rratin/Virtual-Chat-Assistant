package com.chatassistantTest;
import com.chatassistant.LogoutServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private RequestDispatcher requestDispatcher;

    private LogoutServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new LogoutServlet();

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws Exception {
        when(session.getAttribute("user")).thenReturn("user");

        servlet.doGet(request, response);

        verify(session).invalidate(); 
        verify(response).sendRedirect("login.jsp"); 
    }

    @Test
    public void testDoPost() throws Exception {
        servlet.doPost(request, response);

        verify(request, never()).getParameter(anyString()); 
        verify(request, never()).setAttribute(anyString(), any()); 
        verify(requestDispatcher, never()).forward(request, response); 
    }
}
