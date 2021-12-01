package com.br.uvv.tcc.gateway.exceptions;

import org.springframework.http.HttpStatus;

import com.br.uvv.tcc.exceptions.ExceptionBase;

import lombok.Getter;

@Getter
public class ErrorToAccessDatabase extends ExceptionBase {

	private static final long serialVersionUID = 8456382802422976833L;

	private final String code = "uvv.tcc.service.gateway.errorToAccessDatabase";//NOSONAR
	private final String message = "Error to access database.";//NOSONAR
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;//NOSONAR
}
