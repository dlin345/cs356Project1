package edu.cpp.cs356.IVoteSimulator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Question {

	private String question;
	private int selectionLimit = 0;
	
	// ordered mapping
	private LinkedHashMap<String, Integer> candidateAnswers = new LinkedHashMap<String, Integer>();
	private HashMap<String, Student> studentList = new HashMap<String, Student>();
	
	/**
	 * Default constructor for {@link Question}
	 */
	public Question() {
		
	}
	
	/**
	 * Constructs a {@link Question} with the specified question and candidate answers.
	 * @param question the question to be associated with {@code this} {@link Question}
	 * @param candidateAnswers the candidate answers for {@code this} {@link Question}
	 */
	public Question(String question, String[] candidateAnswers) {
		this.question = question;
		setCandidateAnswers(candidateAnswers);
	}
	
	/**
	 * Returns the question associated with {@code this} {@link Question}.
	 * @return the question associated with {@code this} {@link Question}
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * Replaces the question in {@code this} {@link Question} with the specified question
	 * @param question the question to be stored
	 */
	public void setQuestion(String question) {
		this.question = question.toUpperCase();
	}
	
	/**
	 * Sets selection limit by default to total number of candidate answers.
	 */
	public void setSelectionLimit() {
		if (this.selectionLimit == 0) {
			this.selectionLimit = candidateAnswers.size();
		}
	}
	
	/**
	 * Sets selection limit to the specified limit.
	 * @param limit the limit of acceptable number of selected answers
	 */
	public void setSelectionLimit(int limit) {
		// check if selection limit within valid range, set limit
		if (limit > 0 && limit <= candidateAnswers.size()) {
			this.selectionLimit = limit;
		} else if (limit <= 0) {
			this.selectionLimit = 1;
		} else if (limit > candidateAnswers.size()) {
			this.selectionLimit = candidateAnswers.size();
		}
	}
	
	/**
	 * Returns the limit on number the of candidate answers that can be selected.
	 * @return the limit on number of candidate answers that can be selected
	 */
	public int getSelectionLimit() {
		return this.selectionLimit;
	}
	
	/**
	 * Replaces the candidate answers for {@code this} {@link Question} with the specified 
	 * candidate answers.
	 * @param candidateAnswers the candidate answers to be stored
	 */
	public void setCandidateAnswers(String[] candidateAnswers) {
		for(int i = 0; i < candidateAnswers.length; ++i) {
			this.candidateAnswers.put(candidateAnswers[i].toUpperCase(), 0);
		}
		setSelectionLimit();
	}
	
	/**
	 * Returns candidate answers for @this {@link Question}.
	 * @return candidate answers for @this {@link Question}
	 */
	public String[] getCandidateAnswers() {
		String[] candidateAnswers = new String[this.candidateAnswers.size()];
		int index = 0;
		// guarantees fixed order of candidate answers
		for (String response : this.candidateAnswers.keySet()) {
			candidateAnswers[index] = response;
			++index;
		}
		return candidateAnswers;
	}
	
	/**
	 * Prints candidate answers for @this {@link Question}
	 */
	public void printCandidateAnswers() {
		String[] candidateAnswers = getCandidateAnswers();
		for (int i = 0; i < candidateAnswers.length; ++i) {
			System.out.println(candidateAnswers[i]);
		}
		System.out.println("");
	}
	
	/**
	 * Returns the statistics of the submission results.
	 * @return the statistics of the submission results
	 */
	public String[] getCandidateAnswersResults() {
		tallyStudentResponseResults();
		String[] responseData = getCandidateAnswers();
		for (int i = 0; i < responseData.length; ++i) {
			responseData[i] += (" : " + this.candidateAnswers.get(responseData[i]));
		}
		
		return responseData;
	}

	/**
	 * Adds {@code student} to list of {@link Student}s that responded to {@code this} {@link Question}.  
	 * If {@code student} previously submitted a response, the previous response is overwritten.
	 * @param student {@link Student} to be stored in list of students that responded
	 * @throws InvalidResponse
	 */
	public void addStudentResponse(Student student) throws InvalidResponse {
		// checks if number of selected answers exceeds the limit
		if (student.getStudentResponseList().length > this.selectionLimit) {
			throw new InvalidResponse();
		} else {
			// overwrites previously submitted response
			if (studentList.containsKey(student.getID())) {
				System.out.println("Received student resubmission...");
				System.out.println("Already received response from student " + student.getID());
				System.out.println("Overwriting previous response...\n");
			}
			studentList.put(student.getID(), student);
		}
	}
	
	/**
	 * Calculates the statistics of the submission results.
	 */
	private void tallyStudentResponseResults() {
		resetStudentResponseTally();
		
		for (String studentID : studentList.keySet()) {
			String[] studentResponseList = studentList.get(studentID).getStudentResponseList();
			// increment tally for response choice for each student response
			for (int i = 0; i < studentResponseList.length; ++i) {
				if (candidateAnswers.containsKey(studentResponseList[i])) {
					candidateAnswers.put(studentResponseList[i], candidateAnswers.get(studentResponseList[i]) + 1);
				}
			}
		}
	}
	
	/**
	 * Resets the statistics of the submission results.
	 */
	private void resetStudentResponseTally() {
		for (Map.Entry<String, Integer> response : candidateAnswers.entrySet()) {
			candidateAnswers.put(response.getKey(), 0);
		}
	}

}
