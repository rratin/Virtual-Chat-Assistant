package com.chatassistantTest;

import com.chatassistant.ViewDetailsServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.chatassistant.*;
import static org.mockito.Mockito.*;

public class ViewDetailsServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private RequestDispatcher requestDispatcher;
    @Mock private Connection connection;
    @Mock private PreparedStatement preparedStatement;
    @Mock private ResultSet resultSet;

    private ViewDetailsServlet servlet;

    @Mock private ConnectionFactory connectionFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new ViewDetailsServlet();

        // Set up the mock ConnectionFactory
        when(connectionFactory.getConnection()).thenReturn(connection);
        servlet.setConnectionFactory(connectionFactory);

        // Set up other mocks
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the User object
        User mockedUser = mock(User.class);
        when(session.getAttribute("user")).thenReturn(mockedUser);
    }
    @Test
    public void testRetrieveUserDetailsSuccessfully() throws Exception {
        String userEmail = "user@example.com";
        // Use the mocked User object
        User mockedUser = mock(User.class); 

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn(userEmail);
        when(session.getAttribute("user")).thenReturn(mockedUser);
        when(request.getRequestDispatcher("viewDetails.jsp")).thenReturn(requestDispatcher);

        // Mock the database interaction
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        // Mock returning user details from resultSet
        when(resultSet.getString("email")).thenReturn(userEmail);
        when(resultSet.getString("password")).thenReturn("password");
        when(resultSet.getString("fullname")).thenReturn("Full Name");

        servlet.doGet(request, response);

        // Verify interactions with the mocked User object
        verify(mockedUser).setEmail(userEmail);
        verify(mockedUser).setPassword("password");
        verify(mockedUser).setFullname("Full Name");
        verify(request).setAttribute("user", mockedUser);
        verify(requestDispatcher).forward(request, response);
    }

    // Additional test cases go here
}
