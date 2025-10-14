package com.project.filter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import com.project.model.User;
import com.project.service.AuthServiceimp;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {

    @Resource(name = "jdbc/connection")
    private DataSource dataSource;

    private AuthServiceimp authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthServiceimp(dataSource);
    }

    private static final long serialVersionUID = 1L;

    // Public paths
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
        "/AuthController",
        "/login.jsp",
        "/CrudItemsProject/login.jsp",
        "/register.jsp",
        "/CrudItemsProject/register.jsp"
    );

    // Static resource extensions
    private static final List<String> STATIC_EXTENSIONS = Arrays.asList(
        ".css", ".js", ".png", ".jpg", ".jpeg", ".gif", ".ico", ".svg", ".woff", ".woff2", ".ttf"
    );

    // Static resource paths
    private static final List<String> STATIC_PATHS = Arrays.asList(
        "/css/", "/js/", "/images/", "/fonts/", "/assets/",
        "/CrudItemsProject/css/", "/CrudItemsProject/js/", "/CrudItemsProject/images/"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        // ✅ Auto-login via cookies if not already logged in
        if (session == null || session.getAttribute("currentUser") == null) {
            Cookie[] cookies = req.getCookies();
            String email = null;
            String password = null;

            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("userEmail".equals(c.getName())) email = c.getValue();
                    if ("userPass".equals(c.getName())) password = c.getValue();
                }
            }

            if (email != null && password != null) {
                User tempUser = new User(email, password);
                User loggedIn = authService.login(tempUser);
                if (loggedIn!=null) {
                    session = req.getSession(true);
                    session.setAttribute("currentUser", loggedIn); 
                }
            }
        }

        // Relative path
        String path = req.getRequestURI();
        String contextPath = req.getContextPath();
        String relativePath = path.startsWith(contextPath) ? path.substring(contextPath.length()) : path;
        if (!relativePath.startsWith("/")) relativePath = "/" + relativePath;

        // 1️⃣ Allow static resources
        if (isStaticResource(relativePath)) {
            chain.doFilter(request, response);
            return;
        }
     // 🚀 Skip filter checks for logout
        if (relativePath.equalsIgnoreCase("/AuthController") && 
            "logout".equalsIgnoreCase(request.getParameter("action"))) {
            chain.doFilter(request, response);
            return;
        }

        // 2️⃣ Allow public paths
     // 2️⃣ Allow access to public paths
        if (isPublicPath(relativePath)) {
            // Check if user already logged in
            session = req.getSession(false);
            boolean isLoggedIn = (session != null && session.getAttribute("currentUser") != null);

            if (isLoggedIn) {
                // Redirect logged-in user away from login/register pages
                res.sendRedirect(contextPath + "/itemservlet"); // أو أي صفحة محمية رئيسية
                return;
            }

            chain.doFilter(request, response);
            return;
        }


        // 3️⃣ Redirect root to AuthController
        if (relativePath.isEmpty() || relativePath.equals("/")) {
            res.sendRedirect(contextPath + "/AuthController");
            return;
        }

        // 4️⃣ Check authentication
        session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("currentUser") != null);

        if (isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            // Save original URL for redirect after login
            String requestedUrl = path;
            if (req.getQueryString() != null) {
                requestedUrl += "?" + req.getQueryString();
            }
            session = req.getSession(true);
            session.setAttribute("redirectAfterLogin", requestedUrl);

            res.sendRedirect(contextPath + "/AuthController");
        }
    }

    private boolean isStaticResource(String path) {
        for (String staticPath : STATIC_PATHS) {
            if (path.startsWith(staticPath)) return true;
        }
        for (String ext : STATIC_EXTENSIONS) {
            if (path.endsWith(ext)) return true;
        }
        return false;
    }

    private boolean isPublicPath(String path) {
        for (String publicPath : PUBLIC_PATHS) {
            if (path.equals(publicPath) || path.startsWith(publicPath)) return true;
        }
        return false;
    }
}
	