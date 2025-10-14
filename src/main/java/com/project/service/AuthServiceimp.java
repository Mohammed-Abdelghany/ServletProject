package com.project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.model.User;

public class AuthServiceimp implements AuthService {

	private DataSource dataSource;

	public AuthServiceimp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User login(User user) {
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    System.out.println("===> Trying login with: " + user.getEmail() + " / " + user.getPassword());

	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {

	        stmt.setString(1, user.getEmail());
	        stmt.setString(2, user.getPassword());

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                // المستخدم موجود → نملأ بياناته ونرجعها
	                User loggedUser = new User(rs.getString("email"),rs.getString("password"));
	                loggedUser.setId(rs.getLong("id"));
	                loggedUser.setName(rs.getString("name"));
	                
	                return loggedUser;
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // لو مفيش نتيجة، نرجع null
	    return null;
	}
	@Override
	public boolean signup(User user) {
	    String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getEmail());
	        stmt.setString(3, user.getPassword()); // أو مشفر قبل كده

	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	
	}
    public boolean isEmailExists(String email) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn =dataSource .getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    

}

