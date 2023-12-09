package com.chatassistant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/register"; 
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
    private String dbDriver = "com.mysql.jdbc.Driver";

    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public String insert(User user) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        String sql = "INSERT INTO user (email, password,fullname) VALUES (?, ?, ?)"; 
        String result = "Data Entered Successfully";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.executeUpdate();
        } catch (SQLException e) {
            result = "Data Not Entered Successfully";
            e.printStackTrace();
        }
        return result;
    }
}
