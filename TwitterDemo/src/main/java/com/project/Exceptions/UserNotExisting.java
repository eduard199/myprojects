package com.project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotExisting extends Exception {
    public UserNotExisting(String message) {
        super(message);
    }
}
