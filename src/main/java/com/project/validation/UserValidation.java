package com.project.validation;

import java.util.ArrayList;
import java.util.List;

import com.project.model.User;

public class UserValidation {
    private String email;
    private String password;

    public UserValidation(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public List<String> loginValidation() {
        List<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Please insert your email");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Invalid email format");
        }

        if (password == null || password.trim().isEmpty()) {
            errors.add("Please insert your password");
        } else if (password.length() < 6) {
            errors.add("Password must be at least 6 characters");
        }

        return errors;
    }
}
