package com.ss.crm.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: 王顺坤
 * @Date: 2019/1/17 10:41
 * @Description:
 */

public interface ResumeAllotMapper   {
        /*导入csv简历文件到t_student表*/
    Integer ImportResume(@Param("src")String src);


}
