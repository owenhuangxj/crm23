package com.ss.crm.controller;

import com.ss.crm.entity.Log;
import com.ss.crm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class LznController {
    @Autowired
    private LogService ls;
    /*测试*/
    @GetMapping("/log_test")
    public String testVueModule() {
        return "lzn_test";
    }

    /*进入操作日志*/
    @RequestMapping("/typeLog")
    public String typeLog() {
        return "typeLog";
    }

    /*进入日志数据*/
    @RequestMapping("/myLog")
    public String myLog() {
        return "myLog";
    }

    /*添加日志*/

    @RequestMapping("/insertLog")
    @ResponseBody
    public Integer fingInsert(Log log){
        return ls.getInsertLog(log);
    }

    /*查询日志所有内容*/
/*      @SystemControllerLog(actionType="查看", descrption="查看一定时间段的日志")*/
    @RequestMapping(value = "selectLog", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Log> findSelectAll(Log log){
        return ls.getSelectLog(log);
    }

    /*根据条件查找数据*/


}
