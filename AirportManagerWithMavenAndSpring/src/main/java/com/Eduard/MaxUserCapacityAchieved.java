package com.Eduardo;

import org.springframework.stereotype.Component;

@Component
public class MaxUserCapacityAchieved extends Exception {
    MaxUserCapacityAchieved(String message)
    {
        super(message);
    }
}
