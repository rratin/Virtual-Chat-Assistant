package com.chatassistantTest;

import com.chatassistant.DailyTipServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class DailyTipServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private RequestDispatcher requestDispatcher;

    private DailyTipServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new DailyTipServlet();
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(any(), any());
    }

    @Test
    public void testDoGet_TipAvailable() throws ServletException, IOException, SQLException {
        List<String> possibleTips = Arrays.asList(
                "Tip 1: Break your work into small steps to avoid feeling overwhelmed.",
                "Tip 2: Take regular breaks to improve concentration.",
                "Tip 3: Set clear goals to boost productivity.",
                "Tip 4: Maintain a healthy work-life balance for overall wellness.",
                "Tip 5: Practice mindfulness to enhance focus and reduce stress."
        );
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(anyString())).thenAnswer(i -> possibleTips.get(new Random().nextInt(possibleTips.size())));
        ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> attributeValueCaptor = ArgumentCaptor.forClass(Object.class);
        doNothing().when(request).setAttribute(attributeNameCaptor.capture(), attributeValueCaptor.capture());

        servlet.doGet(request, response);
        String capturedAttributeName = attributeNameCaptor.getValue();
        Object capturedAttributeValue = attributeValueCaptor.getValue();
        
        if ("dailyTip".equals(capturedAttributeName) && capturedAttributeValue instanceof String) {
            String tipFromServlet = (String) capturedAttributeValue;
            boolean containsATip = possibleTips.contains(tipFromServlet);
            assertTrue(containsATip);
        } else {
            fail("Expected 'dailyTip' attribute was not set correctly.");
        }
    }
}
