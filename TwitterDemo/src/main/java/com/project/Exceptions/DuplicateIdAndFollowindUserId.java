package com.project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateIdAndFollowindUserId extends SQLException {
    public DuplicateIdAndFollowindUserId(String reason) {
        super(reason);
    }
}
