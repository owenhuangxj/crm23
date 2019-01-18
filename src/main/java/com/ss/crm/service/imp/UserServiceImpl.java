package com.ss.crm.service.imp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ss.crm.entity.User;
import com.ss.crm.mapper.UserMapper;
import com.ss.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String userName, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("user_name", userName)
                .eq("password", password)
        );
        return user;
    }

    @Override
    public User getEmail(String email) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("email", email)
        );
        return user;
    }

    @Override
    public User getUserName(String userName) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("user_name", userName)
        );
        return user;
    }

    @Override
    public int insertUser(String email, String userName, String password) {
        String date =new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreateDate(date);
        int insert = userMapper.insert(user);
        return insert;

    }

    @Override
    public Integer selectUserId(String createDate) {
        Integer count = userMapper.getCount(createDate);
        return count;
    }

}