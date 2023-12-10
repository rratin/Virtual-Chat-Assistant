package com.chatassistantTest;

import com.chatassistant.SetReminderServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class SetReminderServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;

    private SetReminderServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");
        when(request.getParameter("message")).thenReturn("Test Reminder");
        when(request.getParameter("reminderTime")).thenReturn(LocalDateTime.now().toString());

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        servlet = new SetReminderServlet(connection);
    }

    @Test
    public void testSuccessfulReminderSetting() throws Exception {
        servlet.doPost(request, response);

        verify(preparedStatement).executeUpdate();
        verify(response).sendRedirect("home.jsp");
    }
}
