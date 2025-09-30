package com.project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.project.model.User;

public class AuthServiceimp implements AuthService {

	private DataSource dataSource;

	public AuthServiceimp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean login(User user) {
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        System.out.println("===> Trying login with: " + user.getEmail() + " / " + user.getPassword());

	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, user.getEmail());
	        stmt.setString(2, user.getPassword());
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public boolean signup(User user) {
		return false;
	}

}
