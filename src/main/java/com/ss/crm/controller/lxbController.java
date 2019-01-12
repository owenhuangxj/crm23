package com.ss.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lxbController {

    @GetMapping("workMonitor")
    public String testlxb(){

        return  "workMonitor";
    }
}
