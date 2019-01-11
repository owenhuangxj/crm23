package com.ss.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("allot_log")
public class AllotLog extends Model<AllotLog> {

    @TableId
    private Integer id;
    private String createDate;
    private String createid;
    private String editDate;
    private String editid;
    private String counselorid;
    private Integer num;
}
