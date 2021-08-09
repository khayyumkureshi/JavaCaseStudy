package com.casestudy;

public class NoSuchTrainException extends Exception {

	@Override
	public String getMessage() {
		return "Train with given train number does not exist";
	}
	
}
