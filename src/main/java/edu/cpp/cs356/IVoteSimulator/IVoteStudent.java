package edu.cpp.cs356.IVoteSimulator;

import java.util.Random;

public class IVoteStudent implements Student {
	private int Id;
	private Response response;
	
	public IVoteStudent() {
		generateID();
	}
	
	private void generateID() {
		Random rand = new Random();
		// generate 7-digit random number
		this.Id = rand.nextInt(9000000) + 1000000;
	}

	public int getID() {
		return this.Id;
	}
	
	public void setStudentResponse(Response response) {
		this.response = response;
	}
	
	public Response getStudentResponse() {
		return this.response;
	}
	
	public String[] getStudentResponseList() {
		return this.response.getResponseList();
	}
	
	public boolean isSingleChoiceResponse() {
		return response.isSingleChoice();
	}

}
