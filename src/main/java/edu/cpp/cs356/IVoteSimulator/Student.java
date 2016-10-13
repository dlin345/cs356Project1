package edu.cpp.cs356.IVoteSimulator;

public interface Student extends User {

	public String getID();
	public void setStudentResponse(Response response);
	public Response getStudentResponse();
	public String[] getStudentResponseList();
	
}
