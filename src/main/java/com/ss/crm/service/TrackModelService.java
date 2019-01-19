package com.ss.crm.service;

import com.ss.crm.entity.PageData;
import com.ss.crm.entity.TrackModel;

public interface TrackModelService {

    /**
     * 查询学生跟踪信息
     * @param stuName 学生编号
     * @return 跟踪信息
     */
    PageData<TrackModel> getTrackModel(String input, String value, Integer pageNum, Integer pageSize);


}
