package com.shermatov.carparts.exception;

public class DuplicateBoardTitleException extends RuntimeException {

    public DuplicateBoardTitleException(String title) {
        super("Board title already exists: " + title);
    }
}
