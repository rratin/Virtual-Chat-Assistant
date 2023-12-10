package com.chatassistantTest;
import jakarta.servlet.ServletException;
import com.chatassistant.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MovieServletTest {

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

    private movieServlet servlet;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new movieServlet();

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Mock the database connection
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testDoGet_MovieAvailable() throws ServletException, IOException, SQLException {
        // List of possible movies
    	List<String> possibleMovies = Arrays.asList(
                "The Shawshank Redemption", 
                "The Godfather", 
                "The Dark Knight", 
                "Pulp Fiction", 
                "Schindler's List", 
                "Forrest Gump"
            );
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(anyString())).thenAnswer(i -> possibleMovies.get(new Random().nextInt(possibleMovies.size())));

        servlet.doGet(request, response);

        writer.flush();
        String servletResponse = stringWriter.toString();
        boolean containsAMovieTitle = possibleMovies.stream().anyMatch(servletResponse::contains);
        assertTrue(containsAMovieTitle);
        
    }
}
