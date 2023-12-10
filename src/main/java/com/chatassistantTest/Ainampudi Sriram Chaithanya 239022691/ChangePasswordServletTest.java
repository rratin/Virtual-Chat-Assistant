package com.chatassistantTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chatassistant.*;
import static org.mockito.Mockito.*;

public class ChangePasswordServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private ChangePasswordServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new ChangePasswordServlet();

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        // Mock the database connection
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(1); 
    }

    @Test
    public void testPasswordMismatch() throws ServletException, IOException {
        when(request.getParameter("newPassword")).thenReturn("newPassword");
        when(request.getParameter("confirmNewPassword")).thenReturn("differentPassword");

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "New password and confirmation do not match.");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testSuccessfulPasswordChange() throws ServletException, IOException, SQLException {
        String testEmail = "user@example.com";
        String currentPassword = "currentPassword";
        String newPassword = "newPassword";

        when(session.getAttribute("email")).thenReturn(testEmail);
        when(request.getParameter("currentPassword")).thenReturn(currentPassword);
        when(request.getParameter("newPassword")).thenReturn(newPassword);
        when(request.getParameter("confirmNewPassword")).thenReturn(newPassword);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("password")).thenReturn(currentPassword);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        servlet.doPost(request, response);
        verify(request).setAttribute("message", "Password updated successfully.");
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void testIncorrectCurrentPassword() throws ServletException, IOException, SQLException {
        when(session.getAttribute("email")).thenReturn("user@example.com");
        when(request.getParameter("currentPassword")).thenReturn("wrongPassword");
        when(request.getParameter("newPassword")).thenReturn("newPassword");
        when(request.getParameter("confirmNewPassword")).thenReturn("newPassword");
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("password")).thenReturn("currentPassword");

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Incorrect current password.");
        verify(requestDispatcher).forward(request, response);
    }

}
