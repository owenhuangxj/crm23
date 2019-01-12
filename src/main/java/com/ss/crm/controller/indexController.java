package com.ss.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class indexController {

    @GetMapping("/")
    public String index() {

        return "login";
    }

    @GetMapping("/vue_test")
    public String testVueModule() {
        return "test_vue_module";
    }

    @GetMapping("/register")
    public String rto(){
        return "code";
    }

}
