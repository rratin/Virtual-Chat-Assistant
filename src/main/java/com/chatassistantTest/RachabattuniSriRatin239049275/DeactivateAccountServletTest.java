package com.chatassistantTest;

import com.chatassistant.ConnectionFactory;
import com.chatassistant.DeactivateAccountServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;

import static org.mockito.Mockito.*;

public class DeactivateAccountServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ConnectionFactory connectionFactory; // Mock the ConnectionFactory
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private RequestDispatcher requestDispatcher;

    private DeactivateAccountServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        servlet = new DeactivateAccountServlet(connectionFactory); 

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");
        when(preparedStatement.executeUpdate()).thenReturn(1); 
        requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("viewDetails.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testSuccessfulDeactivation() throws Exception {
        servlet.doPost(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect("login.jsp");
    }
    @Test
    public void testFailedDeactivation() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(0); // Simulate failed update

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "An error occurred. Account deactivation was not successful.");
        verify(requestDispatcher).forward(request, response);
    }
}