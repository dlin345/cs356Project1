package edu.cpp.cs356.IVoteSimulator;

public interface Administrator extends User {
	
	public int getID();
	public void addQuestion(Question question);
	public Question getCurrentQuestion();
	
}
