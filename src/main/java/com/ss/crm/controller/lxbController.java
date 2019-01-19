package com.ss.crm.controller;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.User;
import com.ss.crm.service.MonitorService;
import com.ss.crm.service.UserService;
import com.ss.crm.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class lxbController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/workMonitor")
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
     *查询某位员工的所有学员
     * @param userId
     * @param currentPage
     * @param currentPageSize
     * @return
     */
    @GetMapping("/selectWorktorOfStu/{userId}/{currentPage}/{currentPageSize}")
    public @ResponseBody
    List<Student> selectWorktorOfStu(@PathVariable("userId") Integer userId, @PathVariable("currentPage") Integer currentPage, @PathVariable("currentPageSize") Integer currentPageSize) {
        List<Student> students = monitorService.selectWorktorOfStu(userId,currentPage, currentPageSize);
        System.out.println("我获得的信息"+userId+currentPage+currentPageSize);
        return students;
    }

    /**
     * 查询某位员工本月和本周的工作量
     * @param userId
     * @return
     */
    @GetMapping("/selectWorkOfBusiness/{userId}")
    public @ResponseBody
    ChartData selectWorkOfBusiness(@PathVariable("userId") Integer userId) {
        ChartData chartData = monitorService.selectWorkOfBusiness(userId);
//        System.out.println("我获得的数据"+chartData.toString());
        return chartData;
    }

    /**
     *查询某个员工名下的某个学员的跟踪记录
     * @param stuNumber
     * @param userId 20191121870
     * @return
     */
    @GetMapping("/selectWorkOfStuTrak/{stuNumber}/{userId}")
    public @ResponseBody
    List<Track> selectWorkOfStuTrak(@PathVariable("stuNumber") String stuNumber,@PathVariable("userId") Integer userId) {
        List<Track> track = monitorService.selectWorkOfStuTrak(stuNumber,userId);
//        System.out.println("我获得的数据"+chartData.toString());
        return track;
    }
}
