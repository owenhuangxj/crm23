package com.ss.crm.service;

import com.ss.crm.entity.TrackInfo;

import java.util.List;

public interface TrackInfoService {

    /**
     * 添加跟踪记录
     * @param trackInfo 数据
     * @return 影响行数
     */
    boolean addTrackInfoRecord(TrackInfo trackInfo);

    /**
     * 查询学生跟踪信息
     * @param stuNumber 学生编号
     * @return 跟踪信息
     */
    List<TrackInfo> getTrackInfo(String stuNumber);
}
