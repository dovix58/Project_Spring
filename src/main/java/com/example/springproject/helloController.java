package com.example.springproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController
{

    @GetMapping(path = "/hello")
    public String hello(){
        return "Greetings from SPring booot!";
    }
}