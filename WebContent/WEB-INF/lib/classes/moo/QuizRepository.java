package moo;

public class QuizRepository {
	private static QuizRepository instance = null;
	
	private QuizRepository() {}
	
	public QuizRepository getInstance() {
		if (instance == null) {
			instance = new QuizRepository();
		}
		
		return instance;
	}
	
	
	
	
	
	
	
}
