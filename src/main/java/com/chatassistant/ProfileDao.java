package com.chatassistant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDao {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/register";
    private String dbUsername = "root";
    private String dbPassword = "ratin1026";
    private String dbDriver = "com.mysql.jdbc.Driver";

    public boolean updateProfile(User user) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        String sql = "UPDATE user SET fullname = ?, email = ? WHERE id = ?"; 

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());

            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
