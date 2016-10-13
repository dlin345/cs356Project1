package edu.cpp.cs356.IVoteSimulator;

public class MultipleChoiceQuestion extends Question {
	
	/**
	 * Default constructor, sets selection limit to total number of candidate answers.
	 */
	public MultipleChoiceQuestion() {
		super();
	}
	
	/**
	 * Constructs a {@link MultipleChoiceQuestion} with the specified limit on number of answers that can 
	 * be selected.
	 * @param limit the maximum number of answers that can be selected
	 */
	public MultipleChoiceQuestion(int limit) {
		super();
		this.setSelectionLimit(limit);
	}
	
	/**
	 * Constructs a {@link MultipleChoiceQuestion} with the specified question and candidate answers.  The 
	 * maximum number of answers that can be selected is set to the number of candidate answers.
	 * @param question the question to be stored
	 * @param responseChoices the candidate answers
	 */
	public MultipleChoiceQuestion (String question, String[] responseChoices) {
		super(question, responseChoices);
		this.setSelectionLimit(responseChoices.length);
	}
	
	/**
	 * Constructs a {@link MultipleChoiceQuestion} with the specified question, candidate answers, and 
	 * limit on the number of answers that can be selected.
	 * @param question the question to be stored
	 * @param responseChoices the candidate answers
	 * @param limit the maximum number of candidate answers that can be selected
	 */
	public MultipleChoiceQuestion (String question, String[] responseChoices, int limit) {
		super(question, responseChoices);
		this.setSelectionLimit(limit);
	}
}
