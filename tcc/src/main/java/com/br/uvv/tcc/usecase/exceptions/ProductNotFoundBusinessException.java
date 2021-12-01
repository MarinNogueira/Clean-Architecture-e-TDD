package com.br.uvv.tcc.usecase.exceptions;

import org.springframework.http.HttpStatus;

import com.br.uvv.tcc.exceptions.ExceptionBase;

import lombok.Getter;

@Getter
public class ProductNotFoundBusinessException extends ExceptionBase {

	private static final long serialVersionUID = 4508907512321029238L;

	private final String code = "uvv.tcc.service.business.productNotFoundBusinessException";//NOSONAR
	private final String message = "Product not found.";//NOSONAR
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;//NOSONAR
}
