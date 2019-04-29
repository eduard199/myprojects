package com.Eduardo;

import org.springframework.stereotype.Component;


public class DuplicateFlightNumber extends Exception
{
    public DuplicateFlightNumber(String message)
    {
        super(message);
    }
}
