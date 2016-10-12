package edu.cpp.cs356.IVoteSimulator;

public class SimulationDriver {

	public static void main(String[] args) {
		
		int numberOfStudents = 5;
		IVoteServiceSimulation iVoteSimulator = new IVoteServiceSimulation();
		
		// simulate iVote for single-choice question
		iVoteSimulator.preConfigureSingleChoiceQuestion();
		iVoteSimulator.generateRandomSingleChoiceStudents(numberOfStudents);
		iVoteSimulator.printStatistics();
		
		// simulate student re-submission
		iVoteSimulator.generateSingleResponseResubmission();
		iVoteSimulator.printStatistics();
		
		// simulate iVote for multiple-choice question
		iVoteSimulator.preConfigureMultipleChoiceQuestion();
		iVoteSimulator.generateRandomMultipleChoiceStudents(numberOfStudents);
		iVoteSimulator.printStatistics();

		// simulate student re-submission		
		iVoteSimulator.generateMultipleResponseResubmission();
		iVoteSimulator.printStatistics();
		
	}
	

}
