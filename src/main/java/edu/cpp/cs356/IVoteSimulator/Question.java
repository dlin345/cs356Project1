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
	private HashMap<Integer, Student> studentList = new HashMap<Integer, Student>();
	
	public Question() {
		
	}
	
	public Question(String question, String[] responseChoices) {
		this.question = question;
		setResponseChoices(responseChoices);
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question.toUpperCase();
	}
	
	public void setQuestionIsSingleChoice(boolean isSingleChoice) {
		this.singleChoice = isSingleChoice;
		if (!isSingleChoice) {
			multiple = "or more ";
		}
	}
	
	public boolean isSingleChoice() {
		return singleChoice;
	}
	
	/**
	 * Returns appropriate String for plurality
	 * @return
	 */
	public String getIsMultiple() {
		return multiple;
	}
	
	public void setResponseChoices(String[] responseChoices) {
		for(int i = 0; i < responseChoices.length; ++i) {
			this.responseChoices.put(responseChoices[i].toUpperCase(), 0);
		}
	}
	
	/**
	 * Returns String array of possible responses for @this {@link Question}
	 * @return
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
	 * Returns the statistics of the submission results as a String array
	 * @return
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
	 * @param student
	 * @throws InvalidResponse
	 */
	public void addStudentResponse(Student student) throws InvalidResponse {
		if ((!student.isSingleChoiceResponse() && isSingleChoice())) {
			throw new InvalidResponse();
		} else {
			// overwrites previously submitted response
			if (studentList.containsKey(student.getID())) {
				System.out.println("Already received response from student " + student.getID());
				System.out.println("Overwriting previous response...\n");
			}
			studentList.put(student.getID(), student);
		}
	}
	
	private void tallyStudentResponseResults() {
		resetStudentResponseTally();
		
		for (Integer studentID : studentList.keySet()) {
			String[] studentResponseList = studentList.get(studentID).getStudentResponseList();
			// increment tally for response choice for each student response
			for (int i = 0; i < studentResponseList.length; ++i) {
				if (responseChoices.containsKey(studentResponseList[i])) {
					responseChoices.put(studentResponseList[i], responseChoices.get(studentResponseList[i]) + 1);
				}
			}
		}
	}
	
	private void resetStudentResponseTally() {
		for (Map.Entry<String, Integer> response : responseChoices.entrySet()) {
			responseChoices.put(response.getKey(), 0);
		}
	}

}
