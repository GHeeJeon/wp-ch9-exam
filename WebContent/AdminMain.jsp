<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Online Exam - Administrator Page</title>
	
		<% String sessionUserId = (session.getAttribute("user_id") != null) ? (String)session.getAttribute("user_id") : null; %>
		<% boolean isLoggedIn = (sessionUserId != null); %>
		<% boolean isAdmin = isLoggedIn && ("admin".equals(sessionUserId)); %>
		
		<%
			if (!isLoggedIn) {
				out.println("<script>");
				out.println("	location.href='AdminLogin.jsp'");
				out.println("</script>");
			}
		%>
		
	</head>

	<body>
	
	
	
	
	
	</body>
	
</html>