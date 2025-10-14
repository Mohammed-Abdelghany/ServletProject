package com.project.service;

import com.project.model.User;

public interface ProfileService {
	public boolean updateProfile(User user);
public User getUserByEmail(String email);

}
