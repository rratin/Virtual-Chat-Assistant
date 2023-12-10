package com.chatassistantTest;

import com.chatassistant.EditProfileServlet;
import com.chatassistant.ConnectionFactory;
import com.chatassistant.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class EditProfileServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;

    private EditProfileServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new EditProfileServlet(connectionFactory);

        when(request.getSession()).thenReturn(session);
        User mockUser = new User();
        mockUser.setId(1);
        when(session.getAttribute("user")).thenReturn(mockUser);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1); 
    }

    @Test
    public void testSuccessfulProfileUpdate() throws Exception {
        when(request.getParameter("fullname")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");

        servlet.doPost(request, response);

        verify(session).setAttribute("fullname", "John Doe");
        verify(session).setAttribute("email", "john@example.com");
        verify(response).sendRedirect("home.jsp");
    }
}
