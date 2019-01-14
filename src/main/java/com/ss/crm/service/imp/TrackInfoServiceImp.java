package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackInfo;
import com.ss.crm.entity.User;
import com.ss.crm.service.TrackInfoService;
import com.ss.crm.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackInfoServiceImp implements TrackInfoService {

    @Autowired
    private TrackService ts;

    @Override
    public boolean addTrackInfoRecord(TrackInfo ti) {
        // 咨询师
        User user = new User();
        user.setUserId(Integer.parseInt(ti.getTrailsman()));
        // 面试官
        User user1 = new User();
        user1.setUserId(Integer.parseInt(ti.getTechnicalInterviewer()));
        // 学生编号
        Student stu = new Student();
        stu.setStuNumber(ti.getStuNumber());
        // 获取对象
        Track track = new Track(ti.getTrackMethod(),ti.getTrackTime(),ti.getCurrentStatus(),ti.getNextTrackTime(),ti.getTrackDuration(),ti.getPredictTime(),ti.getPredictTrade(),null,null,ti.getTrackDesc(),user,stu,user1);
        return ts.addTrackRecord(track);
    }

    @Override
    public List<TrackInfo> getTrackInfo(String stuNumber) {
        // 获取跟踪对象集合
        List<Track> tracks = ts.getTrackInfo(stuNumber);
        // 创建页面跟踪对象集合
        List<TrackInfo> trackInfos= new ArrayList<TrackInfo>();
        // 创建页面跟踪对象
        TrackInfo trackInfo = null;
        // 循环遍历
        for(Track track : tracks){
            trackInfo = new TrackInfo(track.getTrackWays(),track.getStuNumber().getStuLevel(),track.getTrackTime(),track.getTrackStatus(),track.getTrackNextTime(),track.getTrackDuration(),track.getTrackPredictTime(),track.getTrackTurnoverTime(),track.getTrackDetails(),track.getConsultId().getUserName(),track.getStuNumber().getStuNumber(),track.getTeacherId().getUserName());
            trackInfos.add(trackInfo);
        }
        return trackInfos;
    }
}
