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

<%
List<String> orders=(List<String>)session.getAttribute("orders"); 
String new_order=request.getParameter("order");
if(session.getAttribute("orders")==null){
	orders=new ArrayList<>();
}
orders.add(new_order);
session.setAttribute("orders", orders);
	int i=1;
    out.println("<h1>Your orders is</h1>");
for(String order:orders){

	    out.println("<p> "+i+"-" + order + "</p>");
	    i++;
	    
}
%>
</body>
</html>