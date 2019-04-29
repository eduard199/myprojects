package com.Eduardo;

import org.springframework.stereotype.Component;


public class DuplicateUserName extends Exception
{
    public DuplicateUserName(String message)
    {
        super(message);
    }
}
