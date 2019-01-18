package com.ss.crm;

import com.ss.crm.entity.Message;
import com.ss.crm.entity.TrackInfo;
import com.ss.crm.entity.User;
import com.ss.crm.mapper.StuMapper;
import com.ss.crm.service.MessageService;
import com.ss.crm.service.TrackInfoService;
import com.ss.crm.service.imp.TrackInfoServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ZjTests {

    @Autowired
    private RedisTemplate<String,Object> rt;

    @Autowired
    private MessageService ms;

    @Autowired
    private TrackInfoService tis;
    @Test
    public void contextLoads() {
    }

    @Test
    public void setrds(){
        List<Message> list = new ArrayList<Message>();
        list.add(new Message(1, 1,2,"标题dd","内容1",0,System.currentTimeMillis()/1000+""));
        list.add(new Message(2,1,2,"标题ee","内容2",0,System.currentTimeMillis()/1000+""));
        list.add(new Message(3, 2,1,"标题ss","内容3",0,System.currentTimeMillis()/1000+""));
        User user = new User();
        user.setUserId(1);
        ms.insertMessage(list,user);

//        rt.opsForZSet().add("readmsg1",new Message(4, 1,2,"标题撒5","内容1",1,System.currentTimeMillis()/1000+""),10013);
//        rt.opsForZSet().add("readmsg1",new Message(5, 1,2,"标题撒4","内容1",1,System.currentTimeMillis()/1000+""),1014);
//
//        rt.opsForZSet().add("deletemsg1",new Message(6, 1,2,"标题撒6","内容1",2,System.currentTimeMillis()/1000+""),10015);
//        Set<Object> set = rt.opsForSet().members("messages");
//        System.out.println(set);

    }

    @Test
    public void getrds(){
//        Set<Object> set = ms.getMessageByUserId(1);
//        System.out.println(set);

    }

    @Test
    public void tsts(){
        List<TrackInfo> info = tis.getTrackInfo("20191141148");
        System.out.println(info);
    }


}

