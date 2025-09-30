package com.project.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.project.model.User;
import com.project.service.AuthServiceimp;
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

		private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String password = request.getParameter("password");
		    String email = request.getParameter("email");
		    User user = new User(email, password);
		    UserValidation userValidation = new UserValidation(user);
		    List<String> errors = userValidation.loginValidation();
		    if (!errors.isEmpty()) {
		        request.setAttribute("errors", errors);
		        request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
		        return;
		    }
		    if (authService.login(user)) {
		        HttpSession session = request.getSession();
		        session.setAttribute("currentUser", user);
		        String remember = request.getParameter("remember");
		        if ("on".equals(remember)) {
		            Cookie emailCookie = new Cookie("userEmail", user.getEmail());
		            emailCookie.setMaxAge(7 * 24 * 60 * 60);
		            emailCookie.setHttpOnly(true);
		            response.addCookie(emailCookie);
		        }
		        response.sendRedirect(request.getContextPath() + "/ItemServlet");
		    } else {
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
