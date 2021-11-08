package com.br.uvv.tcc.usecase.exceptions;

import org.springframework.http.HttpStatus;

import com.br.uvv.tcc.gateway.exceptions.ExceptionBase;

import lombok.Getter;

@Getter
public class IncorrectCredentialsBusinessException extends ExceptionBase {

	private static final long serialVersionUID = 1L;

	private final String code = "uvv.tcc.service.business.incorrectCredentialsBusinessException";//NOSONAR
	private final String message = "Error validate exception.";//NOSONAR
	private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;//NOSONAR
}
