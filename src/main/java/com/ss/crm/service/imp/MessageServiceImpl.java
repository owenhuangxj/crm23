package com.ss.crm.service.imp;

import com.ss.crm.entity.Message;
import com.ss.crm.entity.User;
import com.ss.crm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: crm23
 * @description: 消息服务层实现类
 * @author: August
 * @create: 2019-01-12 18:18
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RedisTemplate<String, Object> rt;

    @Override
    public Set<Object> getMessageList(Integer userId, Integer status) {
        String ZsetPreName = "";
        switch (status) {
            case 0:
                ZsetPreName = "unreadmsg";
                break;
            case 1:
                ZsetPreName = "readmsg";
                break;
            case 2:
                ZsetPreName = "deletemsg";
                break;
            default:
                break;
        }
        Set<Object> messages = rt.opsForZSet().rangeByScore(ZsetPreName + userId, userId * 10000, (userId + 1) * 10000 - 1);
//        System.out.println("从" + ZsetPreName + userId + "中获取了" + messages.size() + "条");
        return messages;
    }

    @Override
    public Integer insertMessage(List<Message> msgList, User user) {
        //zset集合中，score = userid*10000 + msgid。每个用户的消息总数不能超过9999条
        if (rt.opsForZSet().zCard("unreadmsg" + user.getUserId()) + msgList.size() >= 9999) {
//            System.out.println("用户" + user.getUserName() + "未读信息过多，添加失败");
            return 0;
        }

        //获取最大的score值，取余10000后获得最大的msgid。然后设置新插入的消息的id
        int i = 0;
        Double score = getMaxScoreInZsets(user.getUserId()) + 1;
        for (; i < msgList.size(); i++) {
            Message message = msgList.get(i);
            message.setId(score.intValue() % 10000);
            rt.opsForZSet().add("unreadmsg" + user.getUserId(), message, user.getUserId() * 10000 + message.getId());
            score++;
        }

//        for (; i < msgList.size(); i++) {
//            rt.opsForZSet().add("unreadmsg" + user.getUserId(), msgList.get(i), user.getUserId() * 10000 + msgList.get(i).getId());
//        }
//        System.out.println("添加了" + i + "条到unreadmsg" + user.getUserId());
        return i;
    }

    @Override
    public Boolean setMessageStatus(Integer msgId, Integer oldStatus, Integer userId) {
        String oldZset = "";
        String newZset = "";
        Integer newStatus = 0;
        switch (oldStatus) {
            case 0:
                // 将未读消息移到已读消息
                oldZset = "unreadmsg";
                newZset = "readmsg";
                newStatus = 1;
                break;
            case 1:
                // 将已读消息移到回收站
                oldZset = "readmsg";
                newZset = "deletemsg";
                newStatus = 2;
                break;
            case 2:
                // 将回收站消息还原到已读消息
                oldZset = "deletemsg";
                newZset = "readmsg";
                newStatus = 1;
        }
        oldZset += userId;
        newZset += userId;
        // 将消息从旧集合拿出，并从旧集合删除
        Set<Object> set1 = rt.opsForZSet().rangeByScore(oldZset, userId * 10000 + msgId, userId * 10000 + msgId);

        Message msg = (Message) set1.toArray()[0];
        // 为新插入的消息重设id，先获取score值最大的消息（排在最后的），score去余后得到该id，加一得到新id，然后设置新score给插入进来的消息。
//        Double score = getMaxScoreInZsets(userId);

        // 执行删除和放入之前先判断是否超量。超过9999条提示失败
//        if (score - userId * 10000 >= 9999) {
//            System.out.println(newZset + "中消息数量过多，修改失败");
//            return null;
//        }

        // 重设status
//        msg.setId((score.intValue() % 10000) + 1);
        msg.setStatus(newStatus);
        // 放入新集合
        Boolean add = rt.opsForZSet().add(newZset, msg, userId * 10000 + msgId);
        //删除旧集合中的该条消息
        rt.opsForZSet().removeRangeByScore(oldZset, userId * 10000 + msgId, userId * 10000 + msgId);
        return add;
    }

    @Override
    public Double getMaxScoreInZsets(Integer userid) {
        String[] zsetArr = {"readmsg" + userid, "unreadmsg" + userid, "deletemsg" + userid};
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < zsetArr.length; i++) {
            // 获取该zset中score最大的对象
            Set<Object> set = rt.opsForZSet().range(zsetArr[i], -1, -1);
            // 通过对象获取score最大值
            if (set.size() > 0) {
                Double score = rt.opsForZSet().score(zsetArr[i], set.toArray()[0]);
                scores.add(score);
            } else {
                scores.add(0.0);
            }
        }
        Double max = Collections.max(scores);
//        System.out.println("最大的score值 ：" + max);
        return max;
    }

    @Override
    public Integer setAllStatus(String func, Integer userId) {
        switch (func) {
            case "readAll":
                // 全部标为已读（unreadmsg集合中的所有消息转移到readmsg中）
                Set<Object> range = rt.opsForZSet().range("unreadmsg" + userId, 0, -1);
                for (Object object : range) {
                    Message msg = (Message) object;
                    msg.setStatus(1);
                    rt.opsForZSet().add("readmsg" + userId, msg, userId * 10000 + msg.getId());
                }
//                System.out.println("从unreadmsg中转移了" + range.size() + "条消息到readmsg中");
                rt.delete("unreadmsg" + userId);
                return range.size();
            case "delAll":
                // 删除全部（readmsg集合中的所有消息转移到deletemsg中）
                Set<Object> range1 = rt.opsForZSet().range("readmsg" + userId, 0, -1);
                for (Object object : range1) {
                    Message msg = (Message) object;
                    msg.setStatus(2);
                    rt.opsForZSet().add("deletemsg" + userId, msg, userId * 10000 + msg.getId());
                }
//                System.out.println("从readmsg中转移了" + range1.size() + "条消息到deletemsg中");
                rt.delete("readmsg" + userId);
                return range1.size();
            case "clearAll":
                // 清空回收站（清空deletemsg集合中的所有数据）
                Boolean delete = rt.delete("deletemsg" + userId);
//                System.out.println("删除集合deletemsg"+delete);
                return 1;

        }
        return null;
    }
}
