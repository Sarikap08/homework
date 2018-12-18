package com.synopsys.exception;

public class CalculatorException  extends Exception{
    private static final long serialVersionUID = 1L;

    public CalculatorException(String msg) {
        super(msg);
    }

    public CalculatorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
