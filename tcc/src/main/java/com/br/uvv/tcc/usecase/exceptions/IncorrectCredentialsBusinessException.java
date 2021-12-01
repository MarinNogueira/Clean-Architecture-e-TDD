package com.br.uvv.tcc.usecase.exceptions;

import org.springframework.http.HttpStatus;

import com.br.uvv.tcc.exceptions.ExceptionBase;

import lombok.Getter;

@Getter
public class IncorrectCredentialsBusinessException extends ExceptionBase {

	private static final long serialVersionUID = 2250101491178220005L;

	private final String code = "uvv.tcc.service.business.incorrectCredentialsBusinessException";//NOSONAR
	private final String message = "Error validate exception.";//NOSONAR
	private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;//NOSONAR
}
