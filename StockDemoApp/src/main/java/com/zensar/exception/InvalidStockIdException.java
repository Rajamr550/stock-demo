package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends RuntimeException {
    String msg;

    public InvalidStockIdException() {
	// TODO Auto-generated constructor stub
	this.msg = "invalid id";
    }

    public InvalidStockIdException(String msg) {
	this.msg = msg;
    }

    @Override
    public String toString() {
	return "InvalidStockIdException [msg=" + msg + "]";
    }

}
