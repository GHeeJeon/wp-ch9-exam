<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="moo.domain.Marker" %>
<%@ page import="moo.data.QuizRepository" %>
<%@ page import="moo.util.Parameter" %>
<%@ page import="moo.entity.Quiz" %>
<%@ page import="java.util.*" %>
    
<% request.setCharacterEncoding("UTF-8"); %>

<% Map<String, String> params = Parameter.extract(request); %>
<% List<Quiz> quizzes = QuizRepository.getInstance().retrieveQuizzesFromUserAnswer(params); %>
<% int score = Marker.getScore(params); %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Quiz result</title>
	</head>
	
	<body>
	
		<h1>시험 결과</h1>
		
		<!-- result summary -->
		<table style="width: 200px;">
			<colgroup>
				<col width="40%" />
				<col width="60%" />
			</colgroup>
			
			<tr>
				<td>학번</td>
				<td><%= request.getParameter("hakbun") %></td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td><%= request.getParameter("name") %></td>
			</tr>
			
			<tr>
				<td>점수</td>
				<td><%= score %> / 5</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<a href="ScoreList.jsp">점수 리스트 보기</a>
				</td>
			</tr>
			
		</table>
		
		<div>
			<a href="index.jsp">메인으로 돌아가기</a>
		</div>
		
	</body>
	
	
	<style>
		table, th, td {
 			border: 1px solid black;
		}
	</style>
</html>