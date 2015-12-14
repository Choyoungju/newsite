package com.hanains.mysite.exception;

public class RepositoryException extends RuntimeException{
	
	public RepositoryException(){
		super("Repository Exception");
	}

	
	public RepositoryException(String message){
		super(message);
	}
}
