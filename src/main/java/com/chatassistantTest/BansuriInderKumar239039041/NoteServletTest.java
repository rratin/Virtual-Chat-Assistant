package com.chatassistantTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.chatassistant.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class NoteServletTest {

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

    private NoteServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new NoteServlet();
        servlet.setConnectionFactory(connectionFactory);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void doGetTest() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        assertTrue(stringWriter.toString().contains("Served at: "));
    }

    @Test
    public void doPostTest() throws Exception {
        when(request.getParameter("note")).thenReturn("Test Note");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("test@example.com");

        servlet.doPost(request, response);

        verify(preparedStatement, times(1)).setString(1, "test@example.com");
        verify(preparedStatement, times(1)).setString(2, "Test Note");
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
