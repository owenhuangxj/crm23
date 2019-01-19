package com.ss.crm.service;
import com.ss.crm.entity.User;

import java.util.List;

public interface UserService {
    /*登陆判断*/
    User selectUser(String userName, String password);
    /*注册*/
    //邮箱是否存在
    User getEmail(String email);
    //用户名是否存在
    User getUserName(String userName);
    int insertUser(String email, String userName, String password);
    //登陆成功改变用户状态
    Integer ChangeStatus(User user);
}
