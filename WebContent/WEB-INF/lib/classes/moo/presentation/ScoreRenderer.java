package moo.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import moo.data.ScoreRepository;
import moo.entity.Score;

public class ScoreRenderer {
	private JspWriter writer = null;

	public ScoreRenderer(JspWriter writer) {
		this.writer = writer;
	}
	
	public void renderScores() throws SQLException, IOException {
		List<Score> scores = ScoreRepository.getInstance().getScores();
	
		for (Score score : scores) {
			printScore(score);
		}
	}
	
	private void printScore(Score score) throws IOException {
		writer.println("<tr>");
		writer.println("		<td>" + score.hakbun + "</td>");
		writer.println("		<td>" + score.name + "</td>");
		writer.println("		<td>" + score.grade + "</td>");
		writer.println("		<td>" + score.indate + "</td>");
		writer.println("</tr");
	}
}
