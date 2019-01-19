package com.ss.crm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.crm.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户姓名
     * @param userId 用户id
     * @return 用户姓名
     */
    User getUserNameByUserId(@Param("userId") int userId);


    Integer updateByStatus(@Param("userName")String userName,@Param("status")String status);

}
