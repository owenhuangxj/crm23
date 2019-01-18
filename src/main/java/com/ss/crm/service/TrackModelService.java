package com.ss.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackModel;

import java.util.List;

public interface TrackModelService {

    /**
     * 查询学生跟踪信息
     * @param stuName 学生编号
     * @return 跟踪信息
     */
    List<TrackModel> getTrackModel(String stuName,String userName,String trackWays,Integer pageNum,Integer pageSize);





}
