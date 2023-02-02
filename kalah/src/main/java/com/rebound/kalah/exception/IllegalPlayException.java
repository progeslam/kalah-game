package com.rebound.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IllegalPlayException extends ResponseStatusException {
    public IllegalPlayException(){
        super(HttpStatus.BAD_REQUEST);
    }
}
