package edu.cpp.cs356.IVoteSimulator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Question {

	private String question;
	private boolean singleChoice;
	private String multiple = "";
	
	// ordered mapping
	private LinkedHashMap<String, Integer> responseChoices = new LinkedHashMap<String, Integer>();
	private HashMap<String, Student> studentList = new HashMap<String, Student>();
	
	/**
	 * Default constructor for {@link Question}
	 */
	public Question() {
		
	}
	
	/**
	 * Constructs a {@link Question} with the specified question and candidate answers.
	 * @param question the question to be associated with {@code this} {@link Question}
	 * @param responseChoices the candidate answers for {@code this} {@link Question}
	 */
	public Question(String question, String[] responseChoices) {
		this.question = question;
		setResponseChoices(responseChoices);
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
	 * Sets {@code this} {@link Question} to either a single-choice or multiple-choice question
	 * @param isSingleChoice {@code true} if {@code this} {@link Question} is a 
	 * single-choice question
	 */
	public void setQuestionIsSingleChoice(boolean isSingleChoice) {
		this.singleChoice = isSingleChoice;
		if (!isSingleChoice) {
			multiple = "or more ";
		}
	}
	
	/**
	 * Returns {@code true} if this {@link Question} is a single-choice question.
	 * @return {@code true} if this {@link Question} is a single-choice question
	 */
	public boolean isSingleChoice() {
		return singleChoice;
	}
	
	/**
	 * Returns appropriate String for plurality
	 * @return appropriate String for plurality
	 */
	public String getIsMultiple() {
		return multiple;
	}
	
	/**
	 * Replaces the candidate answers for {@code this} {@link Question} with the specified 
	 * candidate answers.
	 * @param responseChoices the candidate answers to be stored
	 */
	public void setResponseChoices(String[] responseChoices) {
		for(int i = 0; i < responseChoices.length; ++i) {
			this.responseChoices.put(responseChoices[i].toUpperCase(), 0);
		}
	}
	
	/**
	 * Returns candidate answers for @this {@link Question}.
	 * @return candidate answers for @this {@link Question}
	 */
	public String[] getResponseChoices() {
		String[] responseChoices = new String[this.responseChoices.size()];
		int index = 0;
		for (String response : this.responseChoices.keySet()) {
			responseChoices[index] = response;
			++index;
		}
		return responseChoices;
	}
	
	/**
	 * Prints possible responses for @this {@link Question}
	 */
	public void printResponseChoices() {
		String[] responseChoices = getResponseChoices();
		for (int i = 0; i < responseChoices.length; ++i) {
			System.out.println(responseChoices[i]);
		}
	}
	
	/**
	 * Returns the statistics of the submission results.
	 * @return the statistics of the submission results
	 */
	public String[] getResponseChoiceResults() {
		tallyStudentResponseResults();
		String[] responseData = getResponseChoices();
		for (int i = 0; i < responseData.length; ++i) {
			responseData[i] += (" : " + this.responseChoices.get(responseData[i]));
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
		if ((!student.isSingleChoiceResponse() && isSingleChoice())) {
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
				if (responseChoices.containsKey(studentResponseList[i])) {
					responseChoices.put(studentResponseList[i], responseChoices.get(studentResponseList[i]) + 1);
				}
			}
		}
	}
	
	/**
	 * Resets the statistics of the submission results.
	 */
	private void resetStudentResponseTally() {
		for (Map.Entry<String, Integer> response : responseChoices.entrySet()) {
			responseChoices.put(response.getKey(), 0);
		}
	}

}
