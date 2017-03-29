package com.exception;

public class PayErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private PayError error;

	public PayErrorException(PayError error) {
	     super(error.getString());
	     this.error = error;
	}

	public PayError getPayError() {
	     return error;
	}
}
