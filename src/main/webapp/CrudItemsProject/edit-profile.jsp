<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        /* Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Segoe UI", Arial, sans-serif;
        }

        body {
            background-color: #121212;
            color: #e0e0e0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .edit-container {
            background-color: #1e1e1e;
            padding: 40px;
            border-radius: 16px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            animation: fadeIn 0.5s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .edit-container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #00adb5;
            font-weight: 600;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            color: #bdbdbd;
        }

        input {
            width: 100%;
            padding: 12px;
            background-color: #2c2c2c;
            border: 1px solid #333;
            border-radius: 8px;
            color: #e0e0e0;
            font-size: 15px;
            transition: all 0.3s ease;
        }

        input:focus {
            outline: none;
            border-color: #00adb5;
            background-color: #252525;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #00adb5;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .btn:hover {
            background-color: #019ca3;
        }

        .note {
            text-align: center;
            margin-top: 15px;
            font-size: 13px;
            color: #888;
        }

        .note a {
            color: #00adb5;
            text-decoration: none;
        }

        .note a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
  <%
  List<String> errors = (List<String>) request.getAttribute("errors");
  String message = (String) request.getAttribute("message");

%>

<% if (errors != null && !errors.isEmpty()) { %>
    <div style="color:red; margin-bottom:10px;">
        <ul>
            <% for (String err : errors) { %>
                <li><%= err %></li>
            <% } %>
        </ul>
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
<%
	
} 

%>
    <div class="edit-container">
        <h2>Edit Profile</h2>
        <form action="${pageContext.request.contextPath}/ProfileController" method="post">
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" value="${user.name}" required>
            </div>

            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" value="${user.email}" required>
            </div>

            <div class="form-group">
                <label for="password">New Password</label>
                <input type="password" id="password" name="password" placeholder="Enter new password">
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password">
            </div>

            <button type="submit" class="btn">Save Changes</button>

            <div class="note">
                <a href="profile.jsp">← Back to Profile</a>
            </div>
        </form>
    </div>

</body>
</html>
