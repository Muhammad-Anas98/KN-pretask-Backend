package com.ewallet.api.exception;

public class ApplicationException extends RuntimeException {

	private long entityId;
	private String entityName;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(long id) {
		super(String.format("Resource with id %s not found", id));
		this.entityId = id;
	}

}
