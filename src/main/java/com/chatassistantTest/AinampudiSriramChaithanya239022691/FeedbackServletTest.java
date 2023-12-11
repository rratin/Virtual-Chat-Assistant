package com.chatassistantTest;

import com.chatassistant.FeedbackServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class FeedbackServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private PrintWriter writer;

    private FeedbackServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new FeedbackServlet();

        when(response.getWriter()).thenReturn(writer);
        when(writer.append(any(CharSequence.class))).thenReturn(writer);
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(request, response);

        verify(response).getWriter();
        verify(writer).append("Served at: ");
        verify(writer).append(request.getContextPath());
    }
    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("feedback")).thenReturn("Test Feedback");
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("thankYou.jsp")).thenReturn(requestDispatcher);

        servlet.doPost(request, response);
    }


}
