package com.ss.crm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ss.crm.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 王顺坤
 * @Date: 2019/1/18 16:37
 * @Description:
 */
public interface ResumeAllotService {


    /*多条件分页查询*/
    IPage<Student> selectPage(IPage<Student> page, @Param("ew") Wrapper<Student> queryWrapper);
}
