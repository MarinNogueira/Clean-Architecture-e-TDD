package com.br.uvv.tcc.gateway.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ExceptionBase extends RuntimeException {
	private static final long serialVersionUID = 6610924902296213887L;
	
	public abstract String getCode();
	public abstract HttpStatus getHttpStatus();
	
	@Override
	public abstract String getMessage();
}
