//定义了一个自定义异常类IdIsNullException，继承自Exception，用于在某些特定情况下抛出与用户ID为空相关的异常
package edu.cd.exception;

public class IdIsNullException extends Exception {

	public IdIsNullException() {
		// TODO Auto-generated constructor stub
	}

	public IdIsNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IdIsNullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IdIsNullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IdIsNullException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}