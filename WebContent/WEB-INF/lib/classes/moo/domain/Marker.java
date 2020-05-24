package moo.domain;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import moo.entity.Quiz;
import moo.data.QuizRepository;

public class Marker {
	public static int getScore(Map<String, String> params) throws SQLException, UnsupportedEncodingException {
		List<Quiz> quizzes = QuizRepository.getInstance().getAllQuizzes();
		
		int total = 0;
		
        for(Map.Entry<String, String> elem : params.entrySet() ){
        	if (!elem.getKey().matches("^[+-]?\\d+$")) {
        		continue;
        	}
        	
        	int quizNumber = Integer.parseInt(elem.getKey());
        	String userAnswer = elem.getValue();

			Quiz quiz = quizzes.stream()
					.filter((q) -> quizNumber == q.num)
					.findFirst()
					.orElse(null);
			
			if (quiz != null && userAnswer != null && markSingle(quiz, userAnswer)) {
				total++;
			}        
		}
		
		return total;
	}
	
	private static boolean markSingle(Quiz q, String userAnswer) {
		return userAnswer.trim().equals(q.answer.trim());
	}
}