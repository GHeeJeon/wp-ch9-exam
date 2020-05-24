package moo;

import java.sql.*;
import java.util.*;
import moo.entity.Quiz;

public class QuizRepository {
	private static QuizRepository instance = null;
	
	private QuizRepository() {}
	
	public static QuizRepository getInstance() {
		if (instance == null) {
			instance = new QuizRepository();
		}
		
		return instance;
	}

	public List<Quiz> getRandom5Quizzes() throws SQLException {
		ResultSet rawQuizzes = DB.getInstance().query("SELECT * FROM (SELECT * from QUIZ ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM <= 5");
		List<Quiz> results = new ArrayList<Quiz>();
		
		while (rawQuizzes.next()) {
			Quiz quiz = new Quiz();
			
			List<Integer> exampleIndices = getShuffledExampleIndices();
			
			quiz.num = rawQuizzes.getInt(1);
			quiz.question = rawQuizzes.getString(2);
			quiz.type = rawQuizzes.getString(3);
			quiz.image = rawQuizzes.getString(4);
			quiz.ex1 = rawQuizzes.getString(exampleIndices.get(0));
			quiz.ex2 = rawQuizzes.getString(exampleIndices.get(1));
			quiz.ex3 = rawQuizzes.getString(exampleIndices.get(2));
			quiz.ex4 = rawQuizzes.getString(exampleIndices.get(3));
			
			results.add(quiz);
		}
		
		return results;
	}
	
	private List<Integer> getShuffledExampleIndices() {
		List<Integer> exampleIndices = new ArrayList<Integer>();
		
		exampleIndices.add(5);
		exampleIndices.add(6);
		exampleIndices.add(7);
		exampleIndices.add(8);
		
		Collections.shuffle(exampleIndices);
		
		return exampleIndices;
	}
}
