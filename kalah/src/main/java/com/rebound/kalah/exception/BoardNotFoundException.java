package com.rebound.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BoardNotFoundException extends ResponseStatusException {

    public BoardNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Not found");
    }
}
