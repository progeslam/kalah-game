package com.rebound.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongPlayException extends ResponseStatusException {
    public WrongPlayException(){
        super(HttpStatus.BAD_REQUEST);
    }
}
