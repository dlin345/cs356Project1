package edu.cpp.cs356.IVoteSimulator;

public class SingleChoiceQuestion extends Question {

	/**
	 * Default constructor for {@link SingleChoiceQuestion}, sets selection 
	 * limit to one.
	 */
	public SingleChoiceQuestion() {
		super();
		this.setSelectionLimit(1);
	}
	
	/**
	 * Constructs a {@link SingleChoiceQuestion} with the specified question and 
	 * candidate answers.  The maximum number of answers that can be selected is 
	 * set to one.
	 * @param question the question to be stored
	 * @param responseChoices the candidate answers
	 */
	public SingleChoiceQuestion (String question, String[] responseChoices) {
		super(question, responseChoices);
		this.setSelectionLimit(1);
	}
}
