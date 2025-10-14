package com.project.service;

import com.project.model.User;

public interface AuthService {
public User login(User user);
public boolean signup(User user);

}
