package edu.cpp.cs356.IVoteSimulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class IVoteService {
	
	private HashMap<String, Administrator> iVoteAdminstrators = new HashMap<String, Administrator>();
	private HashMap<String, Student> iVoteStudents = new HashMap<String, Student>();
	
	private Administrator currentIVoteAdmin;
	private Student currentIVoteStudent;
	
	/**
	 * Adds the specified {@link IVoteAdministrator} to the list of {@code Administrator}s
	 * associated with {@code this} {@link IVoteService}.
	 * @param administrator the {@code Administrator} to be added to {@code this} {@link IVoteService}
	 */
	public void addAdministrator(Administrator administrator) {
		currentIVoteAdmin = administrator;
		iVoteAdminstrators.put(administrator.getID(), administrator);
	}
	
	/**
	 * Adds the specified {@link IVoteStudent} to the list of {@code Student}s
	 * associated with {@code this} {@link IVoteService}.
	 * @param student the {@code Student} to be added to {@code this} {@link IVoteService}
	 */
	public void addStudent(Student student) {
		currentIVoteStudent = student;
		iVoteStudents.put(student.getID(), student);
	}
	
	/**
	 * Records the {@link Student} and associated {@link Response} for the current {@link Question} of 
	 * the current {@code Administrator}.  If a {@link Student} has already submitted a {@link Response}, 
	 * then the previous {@link Response} is overwritten.
	 * @param student the {@link Student} whose {@link Response} is to be recorded
	 * @param response the {@link Response} that is to be recorded
	 */
	public void recordStudentResponse(Student student, Response response) {
		currentIVoteStudent = student;
		currentIVoteStudent.setStudentResponse(response);
		
		try {
			currentIVoteAdmin.getCurrentQuestion().addStudentResponse(currentIVoteStudent);
		} catch (InvalidResponse e) {
			System.out.println("Invalid Response: too many responses\n");
		}
	}
	
	/**
	 * Prints the candidate answers for the current {@link Question} and the submission 
	 * results for each possible answer to the console.
	 */
	public void printStatistics() {
		System.out.println("\n***** SUBMISSION RESULTS *****");
		System.out.println("QUESTION: " + currentIVoteAdmin.getCurrentQuestion().getQuestion());
		
		String[] results = currentIVoteAdmin.getCurrentQuestion().getResponseChoiceResults();
		for (String responseResult : results) {
			System.out.println(responseResult);
		}
	}
	
	/**
	 * Prints the current {@link Question} and its list of candidate answers to the console.
	 */
	public void printQuestionAndResponses() {
		System.out.println("Question: " + currentIVoteAdmin.getCurrentQuestion().getQuestion() + "\n");
		System.out.println("Choose one " + currentIVoteAdmin.getCurrentQuestion().getIsMultiple() + "of the following: ");
		currentIVoteAdmin.getCurrentQuestion().printResponseChoices();
	}
	
	/*
	 * Methods below were used to test implementation by receiving input from console
	 */
	
	/**
	 * Records the {@link Student} and associated {@link Response} for the current {@link Question} of 
	 * the current {@code Administrator} based on user input from the console.  If a {@link Student} 
	 * has already submitted a {@link Response}, then the previous {@link Response} is overwritten. 
	 */
	public void recordStudentResponseFromConsole() {
		recordStudentResponse(new IVoteStudent(), generateResponseFromConsole());
	}
	
	/**
	 * Generates a {@link Response} based on user input from the console.
	 * @return the {@link Response} that is generated from user input
	 */
	public Response generateResponseFromConsole() {
		Response response = new Response();
		
		System.out.println("Enter response (use comma as separator if multiple responses accepted). "
				+ "Hit \"Enter\" when done.");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine().replaceAll("\\s", "");
			response.setResponseList(input.toUpperCase().split(","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Generates multiple {@link Student}s and corresponding {@link Response}s based on user 
	 * input from the console.
	 */
	public void gatherMultipleStudentResponsesFromConsole() {
		boolean gather = true;
		String newResponse = "";
		
		while (gather) {
			System.out.println("\nWould you like to enter a new response? (Y/N): ");
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				newResponse = reader.readLine();
			
				if (newResponse.equalsIgnoreCase("Y")) {
					recordStudentResponseFromConsole();
				} else if (newResponse.equalsIgnoreCase("N")) {
					gather = false;
				} else {
					throw new InvalidResponse();
				}
			} catch (InvalidResponse e) {
				gather = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns an {@code Administrator} whose current {@link Question} and candidate answers are 
	 * configured from user input from the console. 
	 * @return the {@code Administrator} with current {@link Question} and candidate answers that are 
	 * configured from user input from the console
	 */
	public Administrator administratorSetupInConsole() {
		currentIVoteAdmin = new IVoteAdministrator();
		Question question = new Question();

		configureQuestionTypeFromConsole(question);
		configureQuestionFromConsole(question);
		configureCandidateResponsesFromConsole(question);
		
		addAdministrator(currentIVoteAdmin);		
		System.out.println("\n*****Ready to accept student responses*****\n");		
		return this.currentIVoteAdmin;
	}
	
	/**
	 * Configures {@link Question} as either a single-choice or multiple-choice question 
	 * based on user input from the console.
	 * @param question the {@link Question} whose type is to be configured
	 * @return the question with specified type configuration
	 */
	public Question configureQuestionTypeFromConsole(Question question) {
		String input = "";
		while (!input.equals("1") && !input.equals("2")) {
			System.out.println("\nSelect question type"
					+ "\n1. Single choice"
					+ "\n2. Multiple choice"
					+ "\nEnter option number: ");		
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				input = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (input.equals("1")) {
				question.setQuestionIsSingleChoice(true);
			} else if (input.equals("2")) {
				question.setQuestionIsSingleChoice(false);
			} else {
				System.out.println("\n Invalid input");
			}
		}
		return question;
	}
	
	/**
	 * Configures {@link Question} based on user input from the console.
	 * @param question the {@link Question} that is to be configured
	 * @return the {@link Question} configured with specified question that is to be asked
	 */
	public Question configureQuestionFromConsole(Question question) {
		String input = "";
		System.out.println("Enter question: ");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		question.setQuestion(input);
		return question;
	}
	
	/**
	 * Configures the {@link Question}'s candidate responses based on user input from 
	 * the console.
	 * @param question the {@link Question} that is to be configured
	 * @return the {@link Question} configured with the specified candidate answers
	 */
	public Question configureCandidateResponsesFromConsole(Question question) {
		String input = "";
		System.out.println("Enter candidate responses separated by commas. Hit \"Enter\" when done.");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			input = reader.readLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		input = input.replaceAll("\\s", "");
		question.setResponseChoices(input.split(","));
		
		return question;
	}
}
