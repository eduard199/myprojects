package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}