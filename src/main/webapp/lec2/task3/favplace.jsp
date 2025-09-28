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
String fav_place=request.getParameter("fav_place");
if(fav_place!=null ||fav_place.trim().isEmpty()){
Cookie fav_cookie=new Cookie("fav_place",fav_place);
fav_cookie.setMaxAge(30*24*60*60);
response.addCookie(fav_cookie);
}
String savedPlace=null;
Cookie[] cookies = request.getCookies();
if (cookies != null){
	for (Cookie c : cookies){
		if("fav_place".equals(c.getName())){
			savedPlace=c.getValue();
			break;
		}
	}
}
out.println("<h2>Welcome to Home Page</h2>");

if (savedPlace != null) {
    out.println("<p>Your favorite place is: <strong>" + savedPlace + "</strong></p>");
} else {
    out.println("<p>No favorite place found, please go back and enter it.</p>");
}
%>

</body>
</html>