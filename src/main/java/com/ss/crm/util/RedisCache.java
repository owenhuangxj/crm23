package com.ss.crm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisCache<T> {

    @Autowired
    private RedisTemplate<String, Object> rt;

    /**
     * 添加缓存信息
     *
     * @param t    数据
     * @param keys 键
     * @return 是否添加成功
     */
    public void addCache(String keys, T t) {
        Long aLong = rt.opsForList().rightPush(keys, t);
        if (aLong > 0) {
            System.out.println("缓存添加成功");
        }
    }

    /**
     * 查询缓存信息
     *
     * @param keys 通过key
     * @return 缓存数据
     */
    public List getCache(String keys) {
        List<Object> list = rt.opsForList().range(keys, 0, -1);
        if (list.size() > 0) {
            System.out.println("从Redis获取到的list:" + list);
        }
        return list;
    }

    /**
     * 删除全部
     * @param keys
     */
    public void updateCache(String keys) {
        System.out.println("清除缓存");
        rt.delete(keys);
    }

}
