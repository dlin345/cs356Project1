package edu.cpp.cs356.IVoteSimulator;

import java.util.Random;

public class IVoteStudent implements Student {
	
	private String id;
	private Response response;
	
	/**
	 * Default constructor for {@link IVoteStudent}.
	 */
	public IVoteStudent() {
		super();
		generateID();
	}
	
	/**
	 * Generates a random 7-digit ID number
	 */
	private void generateID() {
		Random rand = new Random();
		// generate 7-digit random number
		this.id = Integer.toString(rand.nextInt(9000000) + 1000000);
	}

	/**
	 * Returns the ID associated with {@code this} {@link IVoteStudent}.
	 * @return the ID associated with {@code this} {@link IVoteStudent}
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Sets the specified {@link Response} as the {@link Response} to be associated 
	 * with {@code this} {@link IVoteStudent}.
	 * @param response the {@link Response} to be associated with {@code this} {@link IVoteStudent}
	 */
	public void setStudentResponse(Response response) {
		this.response = response;
	}
	
	/**
	 * Returns the {@link Response} associated with {@code this} {@link IVoteStudent}.
	 * @return the {@link Response} associated with {@code this} {@link IVoteStudent}
	 */
	public Response getStudentResponse() {
		return this.response;
	}
	
	/**
	 * Returns the list of candidate answers selected as the {@link Response} for {@code this} {@link IVoteStudent}.
	 * @return the list of candidate answers selected as the {@link Response} for {@code this} {@link IVoteStudent}
	 */
	public String[] getStudentResponseList() {
		return this.response.getResponseList();
	}

}
