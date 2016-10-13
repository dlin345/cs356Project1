package edu.cpp.cs356.IVoteSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IVoteServiceSimulation {
	
	private IVoteService iVote = new IVoteService();
	String[] responseChoices = new String[] {"A", "B", "C", "D"};
	
	ArrayList<Student> singleChoiceStudents = new ArrayList<Student>();
	ArrayList<Student> multipleChoiceStudents = new ArrayList<Student>();

	/**
	 * Configures a predetermined single-choice {@link Question} and its candidate answers.
	 */
	public void preConfigureSingleChoiceQuestion() {
		System.out.println("===================================================");
		System.out.println("Configuring single choice question...");
		
		IVoteAdministrator administrator = new IVoteAdministrator();
		Question question = new SingleChoiceQuestion("Choose one of the following", responseChoices);
		
		administrator.addQuestion(question);
		this.iVote.addAdministrator(administrator);
		this.iVote.printQuestionAndResponses();
	}
	
	/**
	 * Configures a predetermined multiple-choice {@link Question} and its 
	 * candidate answers.
	 */
	public void preConfigureMultipleChoiceQuestion() {
		System.out.println("===================================================");
		System.out.println("Configuring multiple choice question...");
		
		Administrator administrator = new IVoteAdministrator();
		Question question = new MultipleChoiceQuestion("Choose one or more of the following", responseChoices);
		
		administrator.addQuestion(question);
		this.iVote.addAdministrator(administrator);
		this.iVote.printQuestionAndResponses();
	}
	
	/**
	 * Generates a random {@link Response}.  The number of candidate answers to randomly select may 
	 * not exceed the specified maximum.
	 * @param maxNumberOfResponses the maximum number of candidate answers to randomly select
	 * @return the {@link Response} with randomly selected candidate answers not exceeding the 
	 * specified maximum
	 */
	public Response generateRandomResponse(int maxNumberOfResponses) {
		Response response = new Response();
		
		Random rand = new Random();
		String[] responseList = new String[maxNumberOfResponses];
		for (int i = 0; i < maxNumberOfResponses; ++i) {
			responseList[i] = this.responseChoices[rand.nextInt(responseChoices.length)];
		}
		response.setResponseList(responseList);
		
		return response;
	}
	
	/**
	 * Generates a {@link Student} and a corresponding randomly generated {@link Response} with 
	 * the number of randomly selected candidate answers not exceed the specified maximum.
	 * @param maxNumberOfResponses the maximum number of candidate answers to randomly select
	 * @return a {@link Student} with a randomly generated {@link Response}
	 */
	private Student generateRandomResponseStudent(int maxNumberOfResponses) {
		Student student = new IVoteStudent();
		Response response = generateRandomResponse(maxNumberOfResponses);
		
		iVote.recordStudentResponse(student, response);

		return student;
	}
	
	/**
	 * Generates a {@link Student} with a randomly generated {@link Response}.  The {@link Response} 
	 * associated with the {@link Student} has exactly one randomly selected candidate answer.
	 * @param numberOfStudents the number of {@link Student}s to generate with random 
	 * single-choice {@link Response}s
	 */
	public void generateRandomSingleChoiceStudents(int numberOfStudents) {
		Student currentStudent;
		for (int i = 0; i < numberOfStudents; ++i) {
			currentStudent = generateRandomResponseStudent(1);
			singleChoiceStudents.add(currentStudent);
		}
		printStudentResponses(singleChoiceStudents);
	}
	
	/**
	 * Generates a {@link Student} with a randomly generated {@link Response}.  The {@link Response} 
	 * associated with the {@link Student} has at least one randomly selected candidate answer.
	 * @param numberOfStudents the number of {@link Student}s to generate with random 
	 * multiple-choice {@link Response}s
	 */
	public void generateRandomMultipleChoiceStudents(int numberOfStudents) {
		Random rand = new Random();
		Student currentStudent;
		for (int i = 0; i < numberOfStudents; ++i) {
			currentStudent = generateRandomResponseStudent(rand.nextInt(responseChoices.length - 1) + 1);
			multipleChoiceStudents.add(currentStudent);
		}
		printStudentResponses(multipleChoiceStudents);
	}
	
	/**
	 * Submits a randomly generated single-choice {@link Response} for a {@link Student} that 
	 * has already submitted a {@link Response}.
	 */
	public void generateSingleResponseResubmission() {
		Random rand = new Random();
		Student student = singleChoiceStudents.get(rand.nextInt(singleChoiceStudents.size()));
		Response response = generateRandomResponse(1);
		iVote.recordStudentResponse(student, response);
		
		printStudentResponses(singleChoiceStudents);
	}
	
	/**
	 * Submits a randomly generated multiple-choice {@link Response} for a {@link Student} that 
	 * has already submitted a {@link Response}.
	 */
	public void generateMultipleResponseResubmission() {
		Random rand = new Random();
		Student student = multipleChoiceStudents.get(rand.nextInt(singleChoiceStudents.size()));
		Response response = generateRandomResponse(rand.nextInt(responseChoices.length - 1) + 1);
		iVote.recordStudentResponse(student, response);
		
		printStudentResponses(multipleChoiceStudents);
	}
	
	/**
	 * Prints the statistics of the submission results to the console.
	 */
	public void printStatistics() {
		iVote.printStatistics();
		System.out.println("\n");
	}
	
	/**
	 * Prints each {@link Student}'s ID and corresponding {@link Response} from the specified 
	 * list of {@link Student}s to the console.
	 * @param studentList the list of {@link Student}s whose ID and {@link Response} are to be output 
	 * to the console
	 */
	private void printStudentResponses(ArrayList<Student> studentList) {
		for (Student student : studentList) {
			System.out.println("Student ID: " + student.getID());
			System.out.println("Response: " + Arrays.toString(student.getStudentResponseList()));
		}
		System.out.println("\n");
	}
	
}
