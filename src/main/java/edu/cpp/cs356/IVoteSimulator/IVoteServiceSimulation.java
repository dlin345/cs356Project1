package edu.cpp.cs356.IVoteSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class IVoteServiceSimulation {
	private IVoteService iVote = new IVoteService();
	String[] responseChoices = new String[] {"A", "B", "C", "D"};
	
	ArrayList<Student> singleChoiceStudents = new ArrayList<Student>();
	ArrayList<Student> multipleChoiceStudents = new ArrayList<Student>();

	public void preConfigureSingleChoiceQuestion() {
		System.out.println("===================================================");
		System.out.println("Configuring single choice question...");
		
		IVoteAdministrator administrator = new IVoteAdministrator();
		Question question = new Question("Choose one of the following", responseChoices);
		question.setQuestionIsSingleChoice(true);
		
		administrator.addQuestion(question);
		this.iVote.addAdministrator(administrator);
		System.out.println("Question: " +  question.getQuestion() + "\n");
	}
	
	public void preConfigureMultipleChoiceQuestion() {
		System.out.println("===================================================");
		System.out.println("Configuring multiple choice question...");
		
		Administrator administrator = new IVoteAdministrator();
		Question question = new Question("Choose one or more of the following", responseChoices);
		question.setQuestionIsSingleChoice(false);
		
		administrator.addQuestion(question);
		this.iVote.addAdministrator(administrator);
		System.out.println("Question: " +  question.getQuestion() + "\n");
	}
	
	public Response generateRandomResponse(int maxNumberOfResponses) {
		Response response = new Response();
		
		Random rand = new Random();
		String[] responseList = new String[maxNumberOfResponses];
		for (int i = 0; i < maxNumberOfResponses; ++i) {
			responseList[i] = this.responseChoices[rand.nextInt(responseChoices.length)];
		}
		HashSet<String> uniqueResponses = new HashSet<String>(Arrays.asList(responseList));
		response.setResponseList(uniqueResponses.toArray(new String[uniqueResponses.size()]));
		
		return response;
	}
	
	private Student generateRandomResponseStudent(int maxNumberOfResponses) {
		Student student = new IVoteStudent();
		Response response = generateRandomResponse(maxNumberOfResponses);
		
		iVote.recordStudentResponse(student, response);

		return student;
	}
	
	public void generateRandomSingleChoiceStudents(int numberOfStudents) {
		Student currentStudent;
		for (int i = 0; i < numberOfStudents; ++i) {
			currentStudent = generateRandomResponseStudent(1);
			singleChoiceStudents.add(currentStudent);
		}
		printStudentResponses(singleChoiceStudents);
	}
	
	public void generateRandomMultipleChoiceStudents(int numberOfStudents) {
		Random rand = new Random();
		Student currentStudent;
		for (int i = 0; i < numberOfStudents; ++i) {
			currentStudent = generateRandomResponseStudent(rand.nextInt(responseChoices.length - 1) + 1);
			multipleChoiceStudents.add(currentStudent);
		}
		printStudentResponses(multipleChoiceStudents);
	}
	
	private void resubmitResponse(Student student, Response response) {
		System.out.println("Received student resubmission...");
		iVote.recordStudentResponse(student, response);
	}
	
	public void generateSingleResponseResubmission() {
		Random rand = new Random();
		Student student = singleChoiceStudents.get(rand.nextInt(singleChoiceStudents.size()));
		Response response = generateRandomResponse(1);
		resubmitResponse(student, response);
		
		printStudentResponses(singleChoiceStudents);
	}
	
	public void generateMultipleResponseResubmission() {
		Random rand = new Random();
		Student student = multipleChoiceStudents.get(rand.nextInt(singleChoiceStudents.size()));
		Response response = generateRandomResponse(rand.nextInt(responseChoices.length - 1) + 1);
		resubmitResponse(student, response);
		
		printStudentResponses(multipleChoiceStudents);
	}
	
	public void printStatistics() {
		iVote.printStatistics();
		System.out.println("\n");
	}
	
	private void printStudentResponses(ArrayList<Student> studentList) {
		for (Student student : studentList) {
			System.out.println("Student ID: " + student.getID());
			System.out.println("Response: " + Arrays.toString(student.getStudentResponseList()));
		}
	}
	
}
