<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="moo.domain.Marker" %>
<%@ page import="moo.data.QuizRepository" %>
<%@ page import="moo.data.ScoreRepository" %>
<%@ page import="moo.util.Parameter" %>
<%@ page import="moo.entity.Quiz" %>
<%@ page import="moo.entity.Score" %>
<%@ page import="java.util.*" %>
<%@ page import="moo.presentation.QuizRenderer" %>
    
<% request.setCharacterEncoding("UTF-8"); %>

<% String hakbun = request.getParameter("hakbun"); %>
<% String name = request.getParameter("name"); %>
<% boolean isValid = (hakbun != null && !hakbun.isEmpty()) && (name != null && !name.isEmpty()); %>


<% List<Score> scores = ScoreRepository.getInstance().getScores(); %>
<% boolean isDuplicated = scores.stream().filter((score) -> score.hakbun.equals(hakbun)).findFirst().orElse(null) != null; %>

<% Map<String, String> params = Parameter.extract(request); %>
<% List<Quiz> quizzes = QuizRepository.getInstance().retrieveQuizzesFromUserAnswer(params); %>
<% int score = Marker.getScore(quizzes); %>

<% 
	if (isValid) {
		if (isDuplicated) {
			out.println("<script>alert('이미 응시하셨습니다!'); location.href='StudentMain.jsp';</script>");
		} else {
			Score newScore = new Score();
			
			newScore.hakbun = hakbun;
			newScore.name = name;
			newScore.grade = String.valueOf(score);
			
			ScoreRepository.getInstance().addScore(newScore);
		}
	} else {
		out.println("<script>alert('학번과 이름이 올바르지 않습니다! 하지만 결과는 보여드리죠');</script>");
	}
%>
    
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
		
		<!-- go to main -->
		<div>
			<a href="index.jsp">메인으로 돌아가기</a>
		</div>
		
		<div>
			<h2>틀린 문제 보기와 정답</h2>
		</div>
		
		<!--  quiz reviews -->
		<div>
			<table style="width: 100%">
				<colgroup>
					<col width="10%" />
					<col width="90%" />
				</colgroup>
			
				<tbody>
					<% new QuizRenderer(out).renderQuizReviews(quizzes); %>
				</tbody>
			</table>
		</div>
			
	</body>
	
	<style>
		table, th, td {
 			border: 1px solid black;
		}
	</style>
	
</html>