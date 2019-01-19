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
@TableName("s_user")
public class User extends Model<User> {
    @TableId
    //用户Id
    private Integer userId;
    //新增用户时间
    private String createDate;
    private String createId;
    //更新时间
    private Data editDate;
    private String editId;
    //邮箱
    private String email;
    private boolean enableStatus;
    private boolean isManage;
    private String login;
    //用户名
    private String userName;
    private boolean onjobStatus;
    private String orgId;
    //密码
    private String password;
    //电话
    private String phone;
    //评论，留言
    private String remark;
    //性别
    private int sex;
    //在线状态
    private String status;
    private int type;
}
