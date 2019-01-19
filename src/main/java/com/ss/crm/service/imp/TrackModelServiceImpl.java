package com.ss.crm.service.imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ss.crm.entity.PageData;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackModel;
import com.ss.crm.mapper.TrackMapper;
import com.ss.crm.mapper.TrackModelMapper;
import com.ss.crm.service.TrackModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TrackModelServiceImpl
 * @Description TODO
 * @Author Jack Hu
 * @Date 2019/1/18 11:16
 */
@Service
public class TrackModelServiceImpl implements TrackModelService {

    @Autowired
    private TrackModelMapper tmm;
    @Autowired
    private TrackMapper tm;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageData<TrackModel> getTrackModel(String input, String value, Integer pageNum, Integer pageSize) {
        Integer start = (pageNum - 1) * pageSize;
        List<TrackModel> track = null;
        Integer total = null;
        // 判断用户在下拉框选择的搜索类型
        if ("1".equals(value)) {
            total = tmm.getTotal(input,null,null);
            track = tmm.getTrackModel(input, null, null, start, pageSize);
        } else if ("2".equals(value)) {
            total = tmm.getTotal(null,input,null);
            track = tmm.getTrackModel(null, input, null, start, pageSize);
        } else {
            total = tmm.getTotal(null,null,input);
            track = tmm.getTrackModel(null, null, input, start, pageSize);
        }
        PageData<TrackModel> pageData = new PageData<>(track,total);
        return pageData;
    }
}
