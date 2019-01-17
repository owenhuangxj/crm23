package com.ss.crm.mapper;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonitorMapper  {
    //分页查询员工信息
    List<User> selectAllWorktor(@Param("start") Integer start, @Param("currentPageSize") Integer currentPageSize);
    //查询公司员工的总人数
    Integer selectWorktorNum();
    //查询员工名下的跟踪的学员
    List<Student> selectWorktorOfStu(@Param("userId") Integer userId,@Param("start") Integer start,@Param("currentPageSize")Integer currentPageSize);
}
