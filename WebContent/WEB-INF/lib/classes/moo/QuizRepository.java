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
	
	public List<Quiz> getQuizzes() throws SQLException {
		ResultSet rawQuizzes = DB.getInstance().query("SELECT * from QUIZ");
		List<Quiz> results = new ArrayList<Quiz>();
		
		while (rawQuizzes.next()) {
			Quiz quiz = new Quiz();
			
			quiz.num = rawQuizzes.getInt(1);
			quiz.question = rawQuizzes.getString(2);
			quiz.type = rawQuizzes.getString(3);
			quiz.image = rawQuizzes.getString(4);
			quiz.ex1 = rawQuizzes.getString(5);
			quiz.ex2 = rawQuizzes.getString(6);
			quiz.ex3 = rawQuizzes.getString(7);
			quiz.ex4 = rawQuizzes.getString(8);
			quiz.answer = rawQuizzes.getString(9);
			
			results.add(quiz);
		}
		
		return results;
	}
}
