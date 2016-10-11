package edu.cpp.cs356.IVoteSimulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

//public class IVoteService implements Simulator {
public class IVoteService {
	
	private HashMap<Integer, Administrator> iVoteAdminstrators = new HashMap<Integer, Administrator>();
	private HashMap<Integer, Student> iVoteStudents = new HashMap<Integer, Student>();
	
	private Administrator currentIVoteAdmin;
	private Student currentIVoteStudent;
	
	public void addAdministrator(Administrator administrator) {
		currentIVoteAdmin = administrator;
		iVoteAdminstrators.put(administrator.getID(), administrator);
	}
	
	public void addStudent(Student student) {
		currentIVoteStudent = student;
		iVoteStudents.put(student.getID(), student);
	}
	
	public void recordStudentResponse(Student student, Response response) {
		currentIVoteStudent = student;
		currentIVoteStudent.setStudentResponse(response);
		
		try {
			currentIVoteAdmin.getCurrentQuestion().addStudentResponse(currentIVoteStudent);
		} catch (InvalidResponse e) {
			System.out.println("Invalid Response: too many responses\n");
		}
	}
	
	public void printStatistics() {
		System.out.println("\n***** SUBMISSION RESULTS *****");
		System.out.println("QUESTION: " + currentIVoteAdmin.getCurrentQuestion().getQuestion());
		
		String[] results = currentIVoteAdmin.getCurrentQuestion().getResponseChoiceResults();
		for (String responseResult : results) {
			System.out.println(responseResult);
		}
	}
	
	public void printQuestionAndResponses() {
		System.out.println("Question: " + currentIVoteAdmin.getCurrentQuestion().getQuestion() + "\n");
		System.out.println("Choose one " + currentIVoteAdmin.getCurrentQuestion().getIsMultiple() + "of the following: ");
		currentIVoteAdmin.getCurrentQuestion().printResponseChoices();
	}
	
	/*
	 * Methods below were used to test implementation by receiving input from console
	 */
	
	public void recordStudentResponseFromConsole() {
		recordStudentResponse(new IVoteStudent(), generateResponseFromConsole());
	}
	
	public Response generateResponseFromConsole() {
		Response response = new Response();
		
		System.out.println("Enter response (use comma as separator if multiple responses accepted). Hit \"Enter\" when done.");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine().replaceAll("\\s", "");
			response.setResponseList(input.toUpperCase().split(","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
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
