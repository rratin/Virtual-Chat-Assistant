package com.chatassistantTest;

import com.chatassistant.ViewNotesServlet;
import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ViewNotesServletTest {

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
    @Mock
    private ResultSet resultSet;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ViewNotesServlet servlet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new ViewNotesServlet();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");
        when(request.getRequestDispatcher("viewNotes.jsp")).thenReturn(requestDispatcher);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("note_text")).thenReturn("Note 1", "Note 2");
    }

    @Test
    public void testRetrieveNotesSuccessfully() throws Exception {
        servlet.doGet(request, response);

        verify(request).setAttribute(eq("notes"), any(ArrayList.class));
        verify(requestDispatcher).forward(request, response);
    }
    
}
