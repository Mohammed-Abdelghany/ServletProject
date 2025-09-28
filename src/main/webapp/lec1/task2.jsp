<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String fullName = request.getParameter("fullname");
String password = request.getParameter("password");
String age = request.getParameter("age");
String addressRadio = request.getParameter("addressRadio");
String addressSelect = request.getParameter("addressSelect");
%>
<h2> Submitted Data</h2>
<p><b>Full Name:</b> </p>
<p><b>Password:</b> <%= password %></p>
<p><b>Age:</b> <%= age %></p>
<p><b>Address (Radio):</b> <%= addressRadio %></p>
<p><b>Address (Select):</b> <%= addressSelect %></p>
</body>
</html>