package com.ss.crm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_LOG")
public class Log implements Serializable {
    /*日志的编号*/
    private Integer logId;
    /*操作日志的类型*/
    private String logType;
    /*错误码*/
    private String logErrorCode;
    /*操作日志的内容*/
    private String logContent;
    /*用户id*/
    private Integer userId;
    /*日志创建的时间*/
    private Date logDate;
    /*日志操作人*/
    private String userName;


}
