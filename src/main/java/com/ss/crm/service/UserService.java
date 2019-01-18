package com.ss.crm.service;
import com.ss.crm.entity.User;

import java.util.List;

public interface UserService {
/*登陆判断*/
    User selectUser(String userName, String password);
/*注册*/
    User getEmail(String email);
    User getUserName(String userName);
    int insertUser(String email, String userName, String password);

    Integer selectUserId(String createDate);
}
