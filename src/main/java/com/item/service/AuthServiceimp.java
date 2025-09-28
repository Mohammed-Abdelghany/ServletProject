package com.item.service;

import javax.sql.DataSource;

import com.item.model.User;

public class AuthServiceimp implements AuthService   {

	private DataSource dataSource;
	public AuthServiceimp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean login(User user) {
		
		return false;
	}

	@Override
	public boolean signup(User user) {
		return false;
	}

}
