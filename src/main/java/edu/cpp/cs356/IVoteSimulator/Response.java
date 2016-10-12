package edu.cpp.cs356.IVoteSimulator;

import java.util.Arrays;
import java.util.HashSet;

public class Response {
	
	private String[] responseList = null;
	private boolean isSingleChoice = true;
	
	/**
	 * Default constructor for {@link Response}.
	 */
	public Response() {
		
	}
	
	/**
	 * Constructs a {@link Response} that stores the specified responses
	 * @param responseList the responses to store
	 */
	public Response(String[] responseList) {
		setResponseList(responseList);
	}
	
	/**
	 * Returns the responses stored in {@code this} {@link Response}.
	 * @return the responses stored in {@code this} {@link Response}
	 */
	public String[] getResponseList() {
		return this.responseList;
	}

	/**
	 * Replaces the responses stored with the specified responses.
	 * @param response the responses to be stored
	 */
	public void setResponseList(String[] response) {
		// removes duplicate responses
		HashSet<String> uniqueResponses = new HashSet<String>(Arrays.asList(response));
		this.responseList = uniqueResponses.toArray(new String[uniqueResponses.size()]);
	}
	
	/**
	 * Determines if {@code this} {@link Response} is a single-choice {@link Response}.
	 */
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
	
	/**
	 * Returns {@code true} if {@code this} {@link Response} is a single-choice {@link Response}.
	 * @return {@code true} if {@code this} {@link Response} is a single-choice {@link Response}
	 */
	public boolean isSingleChoice() {
		setIsSingleChoice();
		return this.isSingleChoice;
	}

}
