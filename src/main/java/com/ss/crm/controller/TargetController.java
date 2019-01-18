package com.ss.crm.controller;

import com.ss.crm.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TargetController {
    @Autowired
    private TargetService targetService;

    @GetMapping("getMonthCount")
    @ResponseBody
    public Integer getMonthCount(){
        Integer count = targetService.selectTargetMonthId();
        System.out.println("count:"+count);
        return count;
    }
    @GetMapping("getDayCount")
    @ResponseBody
    public Integer getDayCount(){
        Integer count = targetService.selectTargetDayId();
        System.out.println("count==="+count);
        return count;
    }
}
