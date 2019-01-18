package com.ss.crm.service;

import com.ss.crm.entity.Message;
import com.ss.crm.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @program: crm23
 * @description: 消息服务层
 * @author: August
 * @create: 2019-01-12 18:11
 **/
public interface MessageService {
    /**
     * 根据状态获取缓存中的消息
     * @param userId
     * @return
     */
//    List<Message> getMessageByUserId(Integer userId);
    Set<Object> getMessageList(Integer userId, Integer msgStatus);

    /**
     * 将消息存入缓存
     * @param msgList
     * @param user  消息接收者
     * @return
     */
    Integer insertMessage(List<Message> msgList, User user);

    /**
     *
     * @param msgId
     * @param oldStatus
     * @param userId
     * @return
     */
    Boolean setMessageStatus(Integer msgId,Integer oldStatus, Integer userId);

    /**
     * 获取多个zset中score最大值
     * @param userid
     * @return
     */
    Double getMaxScoreInZsets(Integer userid);

    Integer setAllStatus(String func, Integer userId);
}
