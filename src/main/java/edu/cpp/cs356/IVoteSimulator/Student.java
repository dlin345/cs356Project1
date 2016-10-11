package edu.cpp.cs356.IVoteSimulator;

public interface Student extends User {

	public int getID();
	public void setStudentResponse(Response response);
	public Response getStudentResponse();
	public String[] getStudentResponseList();
	public boolean isSingleChoiceResponse();
}
