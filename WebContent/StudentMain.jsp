<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="moo.presentation.QuizRenderer" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Online Exam - Student Page</title>
	</head>
	
	<body>
	
		<!-- header section: time limit and go to main -->
		<div>
			<span>제한 시간: </span>
			<span id="time_left">n</span>
			<span>초</span>
			<span>
				<a href="index.jsp">메인으로 돌아가기 </a>
			</span>
		</div>
	
		<!-- body section: A big form -->
		<form action="StudentQuizResult.jsp" method="post" id="main_form">
		
			<!-- user information and 'go back' button -->
			<div>
				<label>
		            <span>학번: </span>
		            <input id="input_hakbun" type="text" name="hakbun">
		        </label>
		
		        <label>
		            <span>이름: </span>
		            <input id="input_name" type="text" name="name">
		        </label>
			</div>
		
			<!--  quizzes -->
			<div>
				<table style="width: 100%">
					<colgroup>
						<col width="10%" />
						<col width="90%" />
					</colgroup>
				
					<tbody>
						<% new QuizRenderer(out).renderQuizzes(); %>
					</tbody>
				</table>
			</div>
			
			<button class="submit-button" type="submit">제출</button>
			
		</form>



	
		<script>
			const updateTimeLabel = function(timeLeft) { 
				document.getElementById('time_left').innerText = '' + timeLeft; 
			}
			
			const whenTimerIsDone = function() {
				document.getElementById('main_form').submit();
			};
			
			const totalGivenTime = 120;
			let currentTimeLeft = totalGivenTime;
			
			const doForEverySecond = function() {
				if (currentTimeLeft <= 0) {
					whenTimerIsDone();
				}
				
				updateTimeLabel(currentTimeLeft--);
			}
		
			window.onload = function() {
				doForEverySecond();
				
				setInterval(doForEverySecond, 1000);
			}
			
		</script>
		
	</body>
</html>