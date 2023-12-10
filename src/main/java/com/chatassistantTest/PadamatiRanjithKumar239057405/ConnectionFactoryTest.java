package com.chatassistantTest;

import com.chatassistant.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionFactoryTest {

    private ConnectionFactory connectionFactory;

    @BeforeEach
    public void setUp() {
        connectionFactory = new ConnectionFactory();
    }

    @Test
    public void testGetConnectionSuccess() {
        try {
            Connection connection = connectionFactory.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Unexpected SQLException: " + e.getMessage());
        }
    }
}


