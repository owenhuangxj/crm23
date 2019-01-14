package com.ss.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class indexController {

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/vue_test")
    public String testVueModule() {
        return "test_vue_module";
    }

}
