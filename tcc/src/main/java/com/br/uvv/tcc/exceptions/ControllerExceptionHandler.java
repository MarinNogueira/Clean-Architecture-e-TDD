package com.br.uvv.tcc.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler({ ExceptionBase.class})
	@ResponseBody
	public ResponseEntity<ExceptionJson> sasException(final ExceptionBase e, final HttpServletResponse response) {
		final ExceptionJson exceptionJson = new ExceptionJson(e);
		return new ResponseEntity<>(exceptionJson, new HttpHeaders(), e.getHttpStatus());
	}
	
}
