package com.ss.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class indexController {
@Value("${user.name}")
private String un;
@GetMapping("/demo")
    public String index(){

        return "demo";
    }

}
