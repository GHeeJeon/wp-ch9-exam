<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="moo.domain.Marker" %>
<%@ page import="moo.util.Parameter" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% int score = Marker.getScore(Parameter.extract(request)); %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Quiz result</title>
	</head>
	
	<body>
	
	<%= score %>
	
	
	</body>
	
</html>