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
import java.util.List;
import javax.sql.DataSource;

import com.project.model.User;
import com.project.service.AuthServiceimp;
import com.project.validation.UserValidation;

@WebServlet("/authcontroller")
public class AuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/connection")
    private DataSource dataSource;

    private AuthServiceimp authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthServiceimp(dataSource);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        if (action == null) {
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);

            return;
        }
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "signup":
                signup(request, response);
                break;
            case "logout":
            	logout(request, response);
                break;
            default:
                request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        if (authService.login(user)!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", authService.login(user));

         
            String remember = request.getParameter("remember");
            if ("on".equals(remember)) {
                Cookie emailCookie = new Cookie("userEmail", user.getEmail());
                Cookie passCookie = new Cookie("userPass", user.getPassword());

                emailCookie.setMaxAge(7 * 24 * 60 * 60);
                passCookie.setMaxAge(7 * 24 * 60 * 60);

                emailCookie.setHttpOnly(true);
                passCookie.setHttpOnly(true);
                response.addCookie(emailCookie);
                response.addCookie(passCookie);

            }

            response.sendRedirect(request.getContextPath() + "/ItemServlet");
        } else {
            errors.add("Invalid email or password");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ جلب البيانات من الفورم
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // 2️⃣ التحقق من القيم المدخلة
        UserValidation userValidation = new UserValidation(name, email, password, confirmPassword);
        List<String> errors = userValidation.signupValidation();

       
        // 3️⃣ لو في أخطاء رجّع المستخدم لنفس الصفحة
    
        if (authService.isEmailExists(email)) {
            errors.add("Email already exists. Please use another one.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
            return;
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
            return;
        }

        // 4️⃣ إنشاء كائن المستخدم
        User user = new User(name, email, password);

        // 5️⃣ تنفيذ عملية التسجيل
        boolean success = authService.signup(user);

        // 6️⃣ عرض النتيجة بناءً على النجاح أو الفشل
        if (success) {
            request.setAttribute("message", "✅ Account created successfully! Please login.");
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "❌ Failed to register. Email might already exist.");
            request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
        }
        
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 1️⃣ Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
        	
            session.invalidate();
        }

        // 2️⃣ Delete login cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userEmail".equals(cookie.getName()) || "userPass".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        request.setAttribute("message", "Logged out Success");
        // 3️⃣ Redirect to login page with message
        request.getRequestDispatcher("/CrudItemsProject/login.jsp").forward(request, response);
    }
    	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
