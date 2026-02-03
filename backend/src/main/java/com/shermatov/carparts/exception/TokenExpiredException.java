package com.shermatov.carparts.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Password reset token has expired.");
    }
}
