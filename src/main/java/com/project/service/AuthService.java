package com.project.service;

import com.project.model.User;

public interface AuthService {
public boolean login(User user);
public boolean signup(User user);
}
