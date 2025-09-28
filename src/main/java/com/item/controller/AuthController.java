package com.item.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.item.model.User;
import com.item.service.AuthServiceimp;
import com.project.validation.UserValidation;

@WebServlet("/authcontroller")
public class AuthController extends HttpServlet {
       
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	AuthServiceimp authService;
	public void init() throws ServletException {
		 authService=new AuthServiceimp(dataSource);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action =request.getParameter("action");
	switch(action) {
	case "login":
		login(request,response);
		break;
	case"signup":
		signup(request,response);
		break;
	default:
		login(request,response);
		break;
	}
	
	
	}
private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 String password=request.getParameter("password");
	 String email=request.getParameter("email");
	 User user =new User(email,password);
	 UserValidation userValidation=new UserValidation(user);
	    List<String> errors = userValidation.loginValidation();
	    if (!errors.isEmpty()) {
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
	        return; 
	    }

	if(	authService.login(user)) {
		request.getRequestDispatcher("/CrudItemsProject/items.jsp").forward(request, response);	
		
	}
	else {
        errors.add("Invalid email or password");
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
    }

}
private void signup(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	
	
}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
