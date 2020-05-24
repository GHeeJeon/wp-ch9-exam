package moo.domain;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import moo.entity.Quiz;
import moo.data.QuizRepository;

public class Marker {
	public static int getScore(Map<String, String> params) throws SQLException, UnsupportedEncodingException {
		List<Quiz> quizzes = QuizRepository.getInstance().retrieveQuizzesFromUserAnswer(params);
		
		int total = 0;
		
        for (Quiz q : quizzes) {
        	if (markSingle(q)) {
        		total++;
        	}
        }
		
		return total;
	}
	
	private static boolean markSingle(Quiz q) {
		if (q == null) {
			return false;
		}
		
		if (q.answer == null) {
			return false;
		}
		
		if (q.userAnswer == null) {
			return false;
		}
		
		return q.answer.trim().equals(q.userAnswer.trim());
	}
}