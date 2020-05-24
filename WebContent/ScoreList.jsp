<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="moo.presentation.ScoreRenderer" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<h1>SCORE LIST</h1>
	
		<!--  scores -->
		<div>
			<table style="width: 100%">
				<colgroup>
					<col width="20%" />
					<col width="20%" />
					<col width="10%" />
					<col width="50%" />
				</colgroup>
			
				<thead>
					<tr>
						<th>학번</th>
						<th>이름</th>
						<th>성적</th>
						<th>제출 시간</th>
					</tr>
				</thead>
			
				<tbody>
					<% new ScoreRenderer(out).renderScores(); %>
				</tbody>
			</table>
		</div>
		
		<!-- go to main -->
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