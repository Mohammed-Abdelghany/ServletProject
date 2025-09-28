<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
<%!
Map<String, String> errors = new HashMap<>();
%>
<%
String full_name=request.getParameter("full_name");
String age_param=request.getParameter("age");
int age=0;
try{
	age=Integer.parseInt(age_param);
}
catch(NumberFormatException e ){
	errors.put("age","pls inter correct age");
}
if(age<18)
errors.put("age","pls your age must be greater than 18 years");
if (full_name == null || full_name.equals("")) {
    errors.put("full name", "U must enter your full name");
}
if(errors.isEmpty()){
	out.print("<h2> full name is "+full_name+"</h2>");
	out.print("<h2> your age  is "+age+"</h2>");
}
else{
	for(Map.Entry<String, String> error: errors.entrySet()){
		out.print("<h2> "+error.getKey()+" </h2>");
		out.print("<h2> "+error.getValue()+" </h2>");
	
	}
}

%>
</h2>
</body>
</html>