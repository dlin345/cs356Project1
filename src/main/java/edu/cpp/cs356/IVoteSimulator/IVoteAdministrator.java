package edu.cpp.cs356.IVoteSimulator;

import java.util.ArrayList;
import java.util.Random;

public class IVoteAdministrator implements Administrator {
	
	private int Id;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
	
	public IVoteAdministrator() {
		generateID();
	}
	
	private void generateID() {
		Random rand = new Random();
		
		// generate 7-digit random number
		this.Id = rand.nextInt(9000000) + 1000000;
	}
	
	public void addQuestion(Question question) {
		this.currentQuestion = question;
		this.questionList.add(question);
	}
	
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}

	public int getID() {
		return this.Id;
	}
}
