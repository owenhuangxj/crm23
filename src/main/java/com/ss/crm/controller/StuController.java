package com.ss.crm.controller;

import com.ss.crm.entity.StudentInfo;
import com.ss.crm.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/stu")
public class StuController {

    @Autowired
    private StuInfoService ss;

    /**
     * 手动添加学生简历
     *
     * @param stu 学生数据
     * @return 是否添加成功
     */
    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/addStu")
    public Boolean addStuResume(@RequestBody StudentInfo stu) {
        System.out.println(stu);
        return ss.addStuResume(stu);
    }

    /**
     * 通过学生编号查询学生信息
     *
     * @param stuNumber 学生编号
     * @return 学生信息
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getStu", produces = "application/json;charset=UTF-8")
    public StudentInfo getStuInfo(@RequestParam("stuNumber") String stuNumber) {
        return ss.getStuInfo(stuNumber);
    }

    /**
     * 修改学生信息
     *
     * @param stu 学生数据
     * @return 是否修改成功
     */
    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/updateStu")
    public Boolean updateStuInfo(@RequestBody StudentInfo stu) {
        System.out.println("-----------修改数据---------");
        System.out.println("stu" + stu);
        return ss.updateStuInfo(stu) > 0;
    }


}
