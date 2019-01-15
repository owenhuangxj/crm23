package com.ss.crm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisCache<T> {

    @Autowired
    private RedisTemplate<String,Object> rt;

    /**
     * 添加缓存信息
     * @param t 数据
     * @param keys 键
     * @return 是否添加成功
     */
    public Boolean addCache(String keys, List<T> t){
        for(T tt : t) {
            Long aLong = rt.opsForList().rightPush(keys, tt);
            if (aLong > 0)
                System.out.println("缓存添加成功");
                return true;
        }
        return false;
    }

    /**
     * 查询缓存信息
     * @param keys 通过key
     * @return 缓存数据
     */
    public List<T> getCache(String keys){
        System.out.println("rt:"+rt);
        System.out.println("rt.opsForList():"+rt.opsForList());
        System.out.println("range"+rt.opsForList().range(keys, 0, -1));
//        List<T> list = rt.opsForList().range(keys, 0, -1);
        List<Object> list = rt.opsForList().range(keys, 0, -1);
        if(list.size()>0) {
            System.out.println("从Redis获取到的");
        }
        return null;
    }

}
