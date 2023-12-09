package com.chatassistant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/register";
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
    private String dbDriver = "com.mysql.jdbc.Driver";

    public boolean validate(String email, String password) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        boolean status = false;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    public User getUserByEmail(String email) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        String sql = "SELECT * FROM user WHERE email = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setIsActive(rs.getBoolean("isActive"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
