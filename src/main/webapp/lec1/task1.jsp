<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Button</title>
</head>
<body>
<%! 
String returnString(String name,int id){
	return name+id;
}
%>
<%
int id=4;
String name="moo";
String result = returnString(name, id);
%>
<%=result%>
</body>
</html>
