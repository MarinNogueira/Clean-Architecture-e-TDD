package com.br.uvv.tcc.usecase.exceptions;

import org.springframework.http.HttpStatus;

import com.br.uvv.tcc.exceptions.ExceptionBase;

import lombok.Getter;

@Getter
public class NotEnoughProductInStockBusinessException extends ExceptionBase {

	private static final long serialVersionUID = -7653293702078733374L;

	private final String code = "uvv.tcc.service.business.NotEnoughProductInStockBusinessException";//NOSONAR
	private final String message = "Not enough product to be sold.";//NOSONAR
	private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;//NOSONAR
}
