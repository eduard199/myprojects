package com.Eduardo;

import org.springframework.stereotype.Component;


public class UnexistingFlightName extends Exception {
    UnexistingFlightName(String message)
    {
        super(message);
    }
}
