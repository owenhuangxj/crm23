package com.ss.crm.controller;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.User;
import com.ss.crm.service.MonitorService;
import com.ss.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class lxbController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("workMonitor")
    public String testlxb(){
        return  "workMonitor";
    }

    /**
     * 分页查询公司所有员工信息
     * @param currentPage
     * @param currentPageSize
     * @return
     */
    @GetMapping("/selectAllWorktor/{currentPage}/{currentPageSize}")
    public @ResponseBody
    List<User> selectUser(@PathVariable("currentPage") Integer currentPage, @PathVariable("currentPageSize") Integer currentPageSize) {
        List<User> users = monitorService.selectAllWorktor(currentPage, currentPageSize);
        for (User user:users
             ) {
            System.out.println("users:"+user);
        }
        return users;
    }
    /**
     * 查询公司员工的总人数
     * @return
     */
    @GetMapping("/selectWorktorNum")
    public @ResponseBody
    Integer selectWorktorNum() {
        return monitorService.selectWorktorNum();
    }

    /**
     *
     * @param userId
     * @param currentPage
     * @param currentPageSize
     * @return
     */
    @GetMapping("/selectWorktorOfStu/{userId}/{currentPage}/{currentPageSize}")
    public @ResponseBody
    List<Student> selectWorktorOfStu(@PathVariable("userId") Integer userId, @PathVariable("currentPage") Integer currentPage, @PathVariable("currentPageSize") Integer currentPageSize) {
        List<Student> students = monitorService.selectWorktorOfStu(userId,currentPage, currentPageSize);

        return students;
    }
}
