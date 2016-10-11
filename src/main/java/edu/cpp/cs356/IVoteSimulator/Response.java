package edu.cpp.cs356.IVoteSimulator;

public class Response {
	
	private String[] responseList = null;
	private boolean isSingleChoice = true;
	
	public Response() {
		
	}
	
	public Response(String[] responseList) {
		this.responseList = responseList;
	}
	
	public String[] getResponseList() {
		return this.responseList;
	}

	public void setResponseList(String[] reponse) {
		this.responseList = reponse;
	}
	
	private void setIsSingleChoice() {
		int numberOfResponses = 0;
		for (int i = 0; i < responseList.length; ++i) {
			if (responseList[i] != null) {
				numberOfResponses++;
			}
		}
		if (numberOfResponses > 1) {
			this.isSingleChoice = false;
		}
	}
	
	public boolean isSingleChoice() {
		setIsSingleChoice();
		return this.isSingleChoice;
	}

}
