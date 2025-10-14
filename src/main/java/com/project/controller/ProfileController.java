package com.project.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

import com.project.model.User;
import com.project.service.ProfileServiceImp;
import com.project.validation.UserValidation;

@WebServlet("/profilecontroller")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/connection")
	private DataSource dataSource;

	private ProfileServiceImp profileService;

	@Override
	public void init() throws ServletException {
		profileService = new ProfileServiceImp(dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		request.setAttribute("user", user);
		request.setAttribute("message", "Your info updated succsess");

		request.getRequestDispatcher("/CrudItemsProject/edit-profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("currentUser") == null) {
			response.sendRedirect(request.getContextPath() + "/CrudItemsProject/login.jsp");
			return;
		}

		User currentUser = (User) session.getAttribute("currentUser");

		// 📥 البيانات القادمة من الفورم
		String name = request.getParameter("name");
		String email = currentUser.getEmail(); // نجيبها من الجلسة (ما تتغيرش)
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// ✅ تحقق من المدخلات
		UserValidation UserValidation = new UserValidation(name, email, password, confirmPassword);
		List<String> errors = UserValidation.editProfileValidation();

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", currentUser);
			request.getRequestDispatcher("/CrudItemsProject/edit-profile.jsp").forward(request, response);
			return;
		}

		// ⚙️ تحديث البيانات
		User updatedUser = new User(name, email, password);

		boolean success = profileService.updateProfile(updatedUser);

		if (success) {
			currentUser.setName(name);
			if (password != null && !password.trim().isEmpty()) {
				currentUser.setPassword(password);
			}
			session.setAttribute("currentUser", currentUser);
			request.setAttribute("message", "✅ Profile updated successfully!");
		} else {
			request.setAttribute("error", "❌ Failed to update profile. Try again.");
		}

		request.setAttribute("user", currentUser);
		request.getRequestDispatcher("/CrudItemsProject/edit-profile.jsp").forward(request, response);
	}
}
