package moo.data;

import java.sql.*;
import java.util.*;

import moo.entity.Score;

public class ScoreRepository {
	private static ScoreRepository instance = null;
	
	private ScoreRepository() {}
	
	public static ScoreRepository getInstance() {
		if (instance == null) {
			instance = new ScoreRepository();
		}
		
		return instance;
	}
	
	public List<Score> getScores() throws SQLException {
		ResultSet rawScores = DB.getInstance().query("SELECT * from SCORE");
		List<Score> results = new ArrayList<Score>();
		
		while (rawScores.next()) {
			Score score = new Score();
			
			score.hakbun = rawScores.getString(1);
			score.name = rawScores.getString(2);
			score.grade = rawScores.getString(3);
			score.indate = rawScores.getString(4);
			
			results.add(score);
		}
		
		return results;
	}
	
	public void addScore(Score score) {
		DB.getInstance().query(
				"INSERT INTO SCORE(S_NUM, S_NAME, S_GRADE) VALUES(?, ?, ?)",
				score.hakbun,
				score.name,
				score.grade
			);
	}
}
