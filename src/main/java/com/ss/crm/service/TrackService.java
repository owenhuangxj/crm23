package com.ss.crm.service;

import com.ss.crm.entity.Track;

import java.util.List;

public interface TrackService {

    /**
     * 添加跟踪记录
     * @param track 数据
     * @return 影响行数
     */
    boolean addTrackRecord(Track track);

    /**
     * 查询学生跟踪信息
     * @param stuNumber 学生编号
     * @return 跟踪信息
     */
    List<Track> getTrackInfo(String stuNumber);
}
