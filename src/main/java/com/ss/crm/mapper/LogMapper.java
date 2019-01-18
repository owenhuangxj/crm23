package com.ss.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.crm.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper extends BaseMapper<Log> {


    /*获取用户的操作*/

    /*添加日志*/
    Integer getInsertLog(Log log);
    /*查询日志所有内容*/
    List<Log> getSelectLog(Log log);
    /*根据条件查询日志*/
    List<Log> getSelectClassifyLog(@Param("logType")String logType,@Param("userId")Integer userId,@Param("userName")String userName,@Param("startTime")String startTime,@Param("endTime")String endTime);

}
