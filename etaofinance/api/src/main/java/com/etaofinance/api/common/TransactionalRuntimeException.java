package com.etaofinance.api.common;

public class TransactionalRuntimeException extends RuntimeException{
public TransactionalRuntimeException(String msg) {
	super(msg);
}
public TransactionalRuntimeException() {
	super();
}
}
