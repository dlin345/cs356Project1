package edu.cpp.cs356.IVoteSimulator;

import java.util.ArrayList;
import java.util.Random;

public class IVoteAdministrator implements Administrator {
	
	private String id;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
	
	/**
	 * Default constructor, generates random 7-digit ID
	 */
	public IVoteAdministrator() {
		super();
		generateID();
	}
	
	/**
	 * Generates random 7-digit ID
	 */
	private void generateID() {
		Random rand = new Random();
		
		// generate 7-digit random number
		this.id = Integer.toString(rand.nextInt(9000000) + 1000000);
	}
	
	/**
	 * Adds specified {@link Question} to {@code this} {@code Administrator}'s list
	 * of associated {@link Question}s.
	 * @param question question to be added to {@code this} {@code Administrator}
	 */
	public void addQuestion(Question question) {
		this.currentQuestion = question;
		this.questionList.add(question);
	}
	
	/**
	 * Returns the current {@link Question} that is accepting submissions
	 * @return the current {@link Question} that is accepting submissions
	 */
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}

	/**
	 * Returns the ID number associated with {@code this} {@link IVoteAdministrator}. 
	 * @return the ID number associated with {@code this} {@link IVoteAdministrator}
	 */
	public String getID() {
		return this.id;
	}
}
