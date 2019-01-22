package com.ss.crm;

import com.ss.crm.entity.PageData;
import com.ss.crm.entity.TrackModel;
import com.ss.crm.mapper.TrackModelMapper;
import com.ss.crm.service.TrackModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.List;


/**
 * @ClassName TrackTest
 * @Description TODO
 * @Author Jack Hu
 * @Date 2019/1/18 14:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackTest {

    @Autowired
    private TrackModelService ts;
    @Autowired
    private TrackModelService tms;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TrackModelMapper tmm;

    @Test
    public void setRedis(){
        List<TrackModel> track = tms.getTrackModel1(null, null, 0, 20);
        System.out.println("存进去了：" + track);
//        redisTemplate.opsForList().set("track",1,track);
        redisTemplate.opsForList().leftPush("track",track);
    }

    @Test
    public void getRedis(){
        List track = redisTemplate.opsForList().range("track", 9, -1);
        Integer total = tmm.getTotal(null, null, null);
        System.out.println("track：" + track);
        PageData pageData = new PageData<>(track, total);
        System.out.println("redis里的pageData：" + pageData);
    }

    @Test
    public void test(){
        PageData<TrackModel> trackModel = ts.getTrackModel(null, "1", 1, 10);
        System.out.println("trackModel：" + trackModel);
    }




}
