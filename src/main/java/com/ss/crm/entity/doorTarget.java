package com.ss.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 上门量目标
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_TARGER")
public class doorTarget {
    @TableId
    private Integer targetId; //目标id
    private String userCreateId;//人员系统创建者Id
    private String userEditId;//人员系统更新者Id
    private String userId;//人员系统用户id
    private int type;//人员渠道id（1：网咨 2：招转 3：高校)
    private String createDate;//创建时间
    private String editDate;//更新时间
    private int conversionTarget;//转化量目标
    private int industryAverage;//行业均值
    private int phoneTarget;//上门目标
    private int typeTarget;//团队上门目标
    private String mouth;//月份
    private String typeConversionTarget;//团队关单目标

}
