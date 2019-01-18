package com.ss.crm.service.imp;

import com.ss.crm.entity.Log;
import com.ss.crm.mapper.LogMapper;
import com.ss.crm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper lm;

    /*返回的日志操作状态*/
    public Integer logType(String status){
        switch (status) {
            case "所有类型":
                return null;
            case "用户操作":
                return 1;
            case "添加":
                return 2;
            case "删除":
                return 3;
            case "修改":
                return 4;
            case "查询":
                return 5;
            default:
                return null;
        }
    }



    /*添加日志*/
    @Override
    public Integer getInsertLog(Log log) {
        String date = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(new Date());
        System.out.println("创建日志的时间:" + date);
        return lm.getInsertLog(log);
    }

    /*查询日志所有内容*/
    @Override
    public List<Log> getSelectLog(Log log) {
        return lm.getSelectLog(log);
    }

    /*按要求查找日志内容*/
    @Override
    public List<Log> getAsyncSelectLog(String logType,Integer userId,String userName,String startTime, String endTime) {
        return lm.getAsyncSelectLog(logType,userId,userName,startTime,endTime);
    }


}
