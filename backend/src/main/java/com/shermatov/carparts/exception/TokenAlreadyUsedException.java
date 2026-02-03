package com.shermatov.carparts.exception;

public class TokenAlreadyUsedException extends RuntimeException {
    public TokenAlreadyUsedException() {
        super("Password reset token has already been used.");
    }
}
