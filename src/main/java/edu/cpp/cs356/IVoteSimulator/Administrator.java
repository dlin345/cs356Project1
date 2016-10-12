package edu.cpp.cs356.IVoteSimulator;

public interface Administrator extends User {
	
	public String getID();
	public void addQuestion(Question question);
	public Question getCurrentQuestion();
	
}
