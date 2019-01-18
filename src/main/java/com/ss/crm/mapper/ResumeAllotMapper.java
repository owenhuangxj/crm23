package com.ss.crm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ss.crm.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 王顺坤
 * @Date: 2019/1/17 10:41
 * @Description:
 */

public interface ResumeAllotMapper  extends BaseMapper<Student> {
        /*导入csv简历文件到t_student表*/
    Integer ImportResume(@Param("src")String src);
        /*多条件分页查询*/
    IPage<Student> selectPage(IPage<Student> page, @Param("ew") Wrapper<Student> queryWrapper);
}
