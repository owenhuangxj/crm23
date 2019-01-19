package com.ss.crm.controller;

import com.ss.crm.service.TargetService;
import com.ss.crm.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TargetController {
    @Autowired
    private TargetService targetService;

    @GetMapping("getCount")
    @ResponseBody
    public ChartData getCount(){
        ChartData chartData = targetService.selectFindTarget();
        return chartData;
    }
    @GetMapping("findCount")
    @ResponseBody
    public ChartData findCount(){
        ChartData targetData = targetService.findTargetCount();
        return targetData;
    }
}
