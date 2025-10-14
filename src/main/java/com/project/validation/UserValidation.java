package com.project.validation;

import java.util.ArrayList;
import java.util.List;

import com.project.model.User;

public class UserValidation {
	private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public UserValidation(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserValidation(String name, String email, String password, String confirmPassword) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
    public List<String> signupValidation() {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Please enter your full name");
        } else if (name.length() < 3) {
            errors.add("Name must be at least 3 characters");
        }

        if (email == null || email.trim().isEmpty()) {
            errors.add("Please enter your email");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Invalid email format");
        }

        if (password == null || password.trim().isEmpty()) {
            errors.add("Please enter your password");
        } else if (password.length() < 6) {
            errors.add("Password must be at least 6 characters");
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            errors.add("Please confirm your password");
        } else if (!password.equals(confirmPassword)) {
            errors.add("Passwords do not match");
        }

        return errors;
    }

    public List<String> editProfileValidation() {
        List<String> errors = new ArrayList<>();

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.add("Please enter your full name");
        } else if (name.length() < 3) {
            errors.add("Name must be at least 3 characters");
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errors.add("Please enter your email");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Invalid email format");
        }

        // Validate password (optional)
        if (password != null && !password.trim().isEmpty()) {
            if (password.length() < 6) {
                errors.add("Password must be at least 6 characters");
            }

            if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                errors.add("Please confirm your password");
            } else if (!password.equals(confirmPassword)) {
                errors.add("Passwords do not match");
            }
        }

        return errors;
    }

}
