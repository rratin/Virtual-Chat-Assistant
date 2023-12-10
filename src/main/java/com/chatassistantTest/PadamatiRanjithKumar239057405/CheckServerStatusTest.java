package com.chatassistantTest;

import com.chatassistant.CheckServerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class CheckServerStatusTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private PrintWriter writer;

    private CheckServerStatus servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new CheckServerStatus();

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(request, response);

        verify(response).setContentType("text/plain");
        verify(response).setCharacterEncoding("UTF-8");
        verify(writer).write("All systems operational");
    }

    
}
