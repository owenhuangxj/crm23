package com.ss.crm.controller;

import com.ss.crm.entity.Student;
import com.ss.crm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/stu")
public class StuController {

    @Autowired
    private StuService ss;

    @RequestMapping(value = "/addStu", produces = "application/json;charset=UTF-8")
    public @ResponseBody  Boolean addStuResume(Student stu){
        Integer stuResume = ss.addStuResume(stu);
        return stuResume>0;
    }

    @RequestMapping(value = "getStu", produces = "application/json;charset=UTF-8")
    public @ResponseBody  void getStuInfo(@RequestParam("stuNumber") String stuNumber){
        Student stu = ss.getStuInfo(stuNumber);

    }


}
