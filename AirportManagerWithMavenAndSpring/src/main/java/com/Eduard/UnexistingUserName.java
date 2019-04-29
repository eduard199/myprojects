package com.Eduardo;

import org.springframework.stereotype.Component;


public class UnexistingUserName extends Exception {
    UnexistingUserName(String message)
    {
        super(message);
    }
}
