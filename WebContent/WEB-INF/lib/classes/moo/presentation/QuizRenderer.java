package moo.presentation;

import java.io.*;
import java.util.*;
import javax.servlet.jsp.JspWriter;
import java.sql.SQLException;

import moo.entity.Quiz;
import moo.QuizRepository;

public class QuizRenderer {
	private JspWriter writer = null;

	public QuizRenderer(JspWriter writer) {
		this.writer = writer;
	}
	
	public void renderQuizzes() throws SQLException, IOException {
		List<Quiz> quizzes = QuizRepository.getInstance().getRandom5Quizzes();
		
		System.out.println("renderQuizzes: got quizzes");
		
		for (Quiz q : quizzes) {
			printQuiz(q);
		}
		
	}
	
	private void printQuiz(Quiz q) throws IOException {
		if ("IT".equals(q.type)) {
			printQuestion(q);

			printExample(q, new ExampleMaker() {
				public String makeSample(String text) {
					return "<span>" + text + "</span>";
				}
			});
		}
		
		else if ("TI".equals(q.type)) {
			printQuestion(q);

			printExample(q, new ExampleMaker() {
				public String makeSample(String text) {
					return "<image src=\"image/" + text + "\">";
				}
			});
		}
		
		else if ("TT".equals(q.type)) {
			printQuestion(q);
			
			printExample(q, new ExampleMaker() {
				public String makeSample(String text) {
					return "<span>" + text + "</span>";
				}
			});
		}
		
		else {
			writer.println("Not good!");
		}
	}
	
	private void printQuestion(Quiz q) throws IOException {
		writer.println("<tr>");
		writer.println("	<td>");
		writer.println("		<p style=\"font-weight: bold\">문제 " + q.num + "</p>");
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
