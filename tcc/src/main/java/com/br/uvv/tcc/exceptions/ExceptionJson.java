package com.br.uvv.tcc.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionJson {

	private final String code;
	private final String message;
	private HttpStatus httpStatus;
	
	public ExceptionJson(final ExceptionBase exceptionBase) {
		this.code = exceptionBase.getCode();
		this.message = exceptionBase.getMessage();
		this.httpStatus = exceptionBase.getHttpStatus();
	}

}
