package com.item.service;

import com.item.model.User;

public interface AuthService {
public boolean login(User user);
public boolean signup(User user);
}
