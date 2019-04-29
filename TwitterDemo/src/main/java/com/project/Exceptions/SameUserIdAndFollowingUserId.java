package com.project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SameUserIdAndFollowingUserId extends Exception {
    public SameUserIdAndFollowingUserId(String message) {
        super(message);
    }
}
