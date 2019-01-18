package com.ss.crm.service;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.User;
import com.ss.crm.util.ChartData;

import java.util.List;

public interface MonitorService {
    /**
     * 查询公司所有员工
     * @param currentPage
     * @param currentPageSize
     * @return
     */
    List<User> selectAllWorktor(Integer currentPage, Integer currentPageSize);

    /**
     * 查询公司总人数
     * @return
     */
    Integer selectWorktorNum();

    /**
     * 查询员工下面跟踪的学员
     * @param userId
     * @param currentPage
     * @param currentPageSize
     * @return
     */
    List<Student> selectWorktorOfStu(Integer userId, Integer currentPage, Integer currentPageSize);

    /**
     * 查询员工业务的完成情况
     * @param userId
     * @return
     */
    ChartData selectWorkOfBusiness(Integer userId);
}
