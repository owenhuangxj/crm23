package com.ss.crm.controller;

import com.ss.crm.entity.Student;
import com.ss.crm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/stu")
public class StuController {

    @Autowired
    private StuService ss;

    /**
     * 手动添加学生简历
     * @param stu 学生数据
     * @return 是否添加成功
     */
    @RequestMapping(value = "/addStu", produces = "application/json;charset=UTF-8")
    public @ResponseBody  Boolean addStuResume(Student stu){
        Integer stuResume = ss.addStuResume(stu);
        return stuResume>0;
    }

    /**
     * 通过学生编号查询学生信息
     * @param stuNumber 学生编号
     * @return 学生信息
     */
    @CrossOrigin
    @RequestMapping(value = "/getStu", produces = "application/json;charset=UTF-8")
    public @ResponseBody  Student getStuInfo(@RequestParam("stuNumber") String stuNumber){
        return ss.getStuInfo(stuNumber);
    }

    /**
     * 修改学生信息
     * @param stu 学生数据
     * @return 是否修改成功
     */
    @RequestMapping(value = "/updateStu", produces = "application/json;charset=UTF-8")
    public @ResponseBody Boolean updateStuInfo(Student stu){
        Integer integer = ss.updateStuInfoByStuNumber(stu);
        if(integer>0)
            return true;
        return false;
    }




}
