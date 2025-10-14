<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Servlet CRUD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CrudItemsProject/css/login.css">
</head>
<body>
    <div class="login-wrap">
        <div class="login-html">
            <!-- Tab Navigation -->
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
            <label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up">
            <label for="tab-2" class="tab">Sign Up</label>
            
            <div class="login-form">
                <!-- ========== SIGN IN FORM ========== -->
                <div class="sign-in-htm">
                    <form action="${pageContext.request.contextPath}/AuthController" method="post">
                        <input type="hidden" name="action" value="login">
                        
                        <!-- Error Messages -->
                    <%
    List<String> errors = (List<String>) request.getAttribute("errors");
    String message = (String) request.getAttribute("message");
%>

<% if (errors != null && !errors.isEmpty()) { %>
    <div class="group" style="margin-bottom: 20px;">
        <div style="background: rgba(220, 53, 69, 0.2); 
                    border: 2px solid #dc3545; 
                    border-radius: 25px; 
                    padding: 15px 20px;
                    color: #fff;">
            <% for (String error : errors) { %>
                <p style="margin: 5px 0; font-size: 14px;">⚠️ <%= error %></p>
            <% } %>
        </div>
    </div>
<% } %>

<% if (message != null) { %>
    <div class="group" style="margin-bottom: 20px;">
        <div style="background: rgba(40, 167, 69, 0.2); 
                    border: 2px solid #28a745; 
                    border-radius: 25px; 
                    padding: 15px 20px;
                    color: #fff;">
            <p style="margin: 0; font-size: 14px;">✅ <%= message %></p>
        </div>
    </div>
<% } 
request.removeAttribute("errors");
request.removeAttribute("");
%>
                    
                        <!-- Email Field -->
                        <div class="group">
                            <label for="email" class="label">Email Address</label>
                            <input id="email" 
                                   name="email" 
                                   type="email" 
                                   class="input" 
                                   placeholder="you@example.com"
                                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                                   required 
                                   autofocus>
                        </div>
                        
                        <!-- Password Field -->
                        <div class="group">
                            <label for="password" class="label">Password</label>
                            <input id="password" 
                                   name="password" 
                                   type="password" 
                                   class="input" 
                                   data-type="password"
                                   placeholder="••••••••" 
                                   required>
                        </div>
                        
                        <!-- Remember Me -->
                        <div class="group">
                            <input id="check" type="checkbox" class="check" name="remember">
                            <label for="check">
                                <span class="icon"></span> 
                                Keep me Signed in
                            </label>
                        </div>
                        
                        <!-- Submit Button -->
                        <div class="group">
                            <button type="submit" class="button">Sign In</button>
                        </div>
                        
                        <!-- Divider -->
                        <div class="hr"></div>
                        
                        <!-- Footer Link -->
                        <div class="foot-lnk">
                            <a href="#forgot">Forgot Password?</a>
                        </div>
                    </form>
                </div>
                
                <!-- ========== SIGN UP FORM ========== -->
                <div class="sign-up-htm">
                    <form action="${pageContext.request.contextPath}/AuthController" method="post">
                        <input type="hidden" name="action" value="signup">
                        
                        <!-- Username Field -->
                        <div class="group">
                            <label for="user" class="label">Username</label>
                            <input id="user" 
                                   name="name" 
                                   type="text" 
                                   class="input"
                                   placeholder="Choose a username" 
                                   required>
                        </div>
                        
                        <!-- Email Field -->
                        <div class="group">
                            <label for="signup-email" class="label">Email Address</label>
                            <input id="signup-email" 
                                   name="email" 
                                   type="email" 
                                   class="input"
                                   placeholder="you@example.com" 
                                   required>
                        </div>
                        
                        <!-- Password Field -->
                        <div class="group">
                            <label for="signup-pass" class="label">Password</label>
                            <input id="signup-pass" 
                                   name="password" 
                                   type="password" 
                                   class="input" 
                                   data-type="password"
                                   placeholder="••••••••" 
                                   required>
                        </div>
                        
                        <!-- Confirm Password Field -->
                        <div class="group">
                            <label for="confirm-pass" class="label">Confirm Password</label>
                            <input id="confirm-pass" 
                                   name="confirmPassword" 
                                   type="password" 
                                   class="input" 
                                   data-type="password"
                                   placeholder="••••••••" 
                                   required>
                        </div>
                        
                        <!-- Submit Button -->
                        <div class="group">
                            <button type="submit" class="button">Sign Up</button>
                        </div>
                        
                        <!-- Divider -->
                        <div class="hr"></div>
                        
                        <!-- Footer Link -->
                        <div class="foot-lnk">
                            <label for="tab-1">Already have an account?</label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Log page load
        console.log('Premium Login Page Loaded');
        console.log('Context Path: ${pageContext.request.contextPath}');
        
        // Optional: Add form validation
        document.addEventListener('DOMContentLoaded', function() {
            const forms = document.querySelectorAll('form');
            forms.forEach(form => {
                form.addEventListener('submit', function(e) {
                    const email = this.querySelector('input[name="email"]');
                    const password = this.querySelector('input[name="password"]');
                    
                    if (email && !email.value.trim()) {
                        e.preventDefault();
                        alert('Please enter your email');
                        email.focus();
                        return;
                    }
                    
                    if (password && !password.value.trim()) {
                        e.preventDefault();
                        alert('Please enter your password');
                        password.focus();
                        return;
                    }
                    
                    console.log('Form submitted');
                });
            });
        });
    </script>
</body>
</html>