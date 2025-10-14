package com.project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.model.User;

public class ProfileServiceImp {

    private DataSource dataSource;

    public ProfileServiceImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ✅ جلب بيانات المستخدم (مثلاً لملف edit-profile.jsp)
    public User getUserByEmail(String email) {
        String query = "SELECT name, email, password FROM users WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ تحديث بيانات المستخدم
    public boolean updateProfile(User user) {
        String query;

        // لو المستخدم دخل باسورد جديد نحدثه، لو لا نسيبه زي ما هو
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            query = "UPDATE users SET name = ?, password = ? WHERE email = ?";
        } else {
            query = "UPDATE users SET name = ? WHERE email = ?";
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
            } else {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
            }

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 // ✅ حذف حساب المستخدم
    public boolean deleteAccount(String email) {
        String query = "DELETE FROM users WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
