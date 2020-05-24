package moo.presentation;

import java.io.*;

import java.util.*;
import javax.servlet.jsp.JspWriter;
import java.sql.SQLException;

import moo.entity.Quiz;
import moo.data.QuizRepository;

public class QuizRenderer {
	private JspWriter writer = null;

	public QuizRenderer(JspWriter writer) {
		this.writer = writer;
	}
	
	public void renderQuizzes() throws SQLException, IOException {
		List<Quiz> quizzes = QuizRepository.getInstance().getRandom5Quizzes();
				
		for (int i = 0; i < quizzes.size(); i++) {	
			int quizDisplayNumber = i + 1; /* starting from 1 */
			
			printQuiz(quizzes.get(i), quizDisplayNumber);
		}
		
	}
	
	private void printQuiz(Quiz q, int quizDisplayNumber) throws IOException {
		if ("IT".equals(q.type)) {
			printQuestion(q, quizDisplayNumber);
			printExample(q, (text) -> "<span>" + text + "</span>");
		}
		
		else if ("TI".equals(q.type)) {
			printQuestion(q, quizDisplayNumber);
			printExample(q, (text) -> "<image src=\"image/" + text + "\">");
		}
		
		else if ("TT".equals(q.type)) {
			printQuestion(q, quizDisplayNumber);
			printExample(q, (text) -> "<span>" + text + "</span>");
		}
		
		else {
			writer.println("Not good!");
		}
	}
	
	private void printQuestion(Quiz q, int quizDisplayNumber) throws IOException {
		writer.println("<tr>");
		writer.println("	<td>");
		writer.println("		<p style=\"font-weight: bold\">문제 " + quizDisplayNumber + "</p>");
		writer.println("	</td>");
		writer.println("	<td>");
		writer.println("		<p>" + q.question + "</p>");
		writer.println("	</td>");
		writer.println("</tr");
	}
	
	private void printExample(Quiz q, ExampleMaker maker) throws IOException {
		writer.println("<tr>");
		writer.println("	<td></td>");
		writer.println("	<td style=\"width:8%; padding:0.75pt; vertical-align:middle\">");
		writer.println("		<p style=\"margin-top:0pt; margin-bottom:0pt; font-size:12pt\">");
		writer.println("			<input name=\"" + q.num + "\" type=\"radio\" value=\"" + q.ex1 + "\">");
		writer.println(				maker.makeSample(q.ex1));
		writer.println("			<input name=\"" + q.num + "\" type=\"radio\" value=\"" + q.ex2 + "\">");
		writer.println(				maker.makeSample(q.ex2));
		writer.println("			<input name=\"" + q.num + "\" type=\"radio\" value=\"" + q.ex3 + "\">");
		writer.println(				maker.makeSample(q.ex3));
		writer.println("			<input name=\"" + q.num + "\" type=\"radio\" value=\"" + q.ex4 + "\">");
		writer.println(				maker.makeSample(q.ex4));
		writer.println("		</p>");
		writer.println("	</td>");
		writer.println("</tr>");
	}
	
	public interface ExampleMaker {
		public String makeSample(String text);
	}
	
}
