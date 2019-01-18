package com.ss.crm.service.imp;

import com.ss.crm.entity.*;
import com.ss.crm.service.TrackInfoService;
import com.ss.crm.service.TrackService;
import com.ss.crm.util.DateUtil;
import com.ss.crm.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackInfoServiceImp implements TrackInfoService {

    private User user = new User();
    @Autowired
    private TrackService ts;
    @Autowired
    private RedisCache<TrackInfo> cache;

    @Override
    public boolean addTrackInfoRecord(TrackInfo ti) {
//        cache.addCache("track" + ti.getStuNumber(), ti);
        // 学生编号
        Student stu = new Student();
        stu.setStuNumber(ti.getStuNumber());
        // 获取当前时间
        String updateTime = DateUtil.getDateTime();
        // 获取对象
        Track track = new Track(ti.getTrackMethod(), ti.getPriority(), ti.getTrackTime(), ti.getCurrentStatus(), ti.getNextTrackTime(), ti.getTrackDuration(), ti.getPredictTime(), ti.getPredictTrade(), null, null, ti.getTrackDesc(), stu, getConsultId(ti), getTeacherId(ti), updateTime);
        return ts.addTrackRecord(track);
    }

    @Override
    public List<TrackInfo> getTrackInfo(String stuNumber) {
        List<TrackInfo> trackInfos = null;
        // 判断Redis中有没有数据
        if (cache.getCache("track" + stuNumber).size() > 0) {
            return cache.getCache("track" + stuNumber);
        } else {
            // 获取跟踪对象集合
            List<Track> tracks = ts.getTrackInfo(stuNumber);
            // 创建页面跟踪对象集合
            trackInfos = new ArrayList<TrackInfo>();
            // 没有数据数据时，返回null
            if (tracks.size() == 0)
                return trackInfos;
            // 创建页面跟踪对象
            TrackInfo trackInfo = null;
            // 循环遍历
            for (Track track : tracks) {
                trackInfo = new TrackInfo(track.getTrackWays(), track.getTrackPriority(), track.getTrackTime(), track.getTrackStatus(), track.getTrackNextTime(), track.getTrackDuration(), track.getTrackPredictTime(), track.getTrackTurnoverTime(), null, null, track.getTrackDetails(), track.getStuNumber().getStuNumber(), getConsultId(track).getUserName(), getTeacherId(track).getUserName());
                trackInfos.add(trackInfo);
                // 添加缓存
//                cache.addCache("track" + stuNumber, trackInfo);
            }
            System.out.println(trackInfos);
        }
        return trackInfos;
    }

    /**
     * 获取咨询师
     *
     * @param info
     * @return
     */
    public User getConsultId(TrackInfo info) {
        // 咨询师
        if (null != info.getTrailsman()) {
            user.setUserId(Integer.parseInt(info.getTrailsman()));
        }
        return user;
    }

    /**
     * 获取面试官
     *
     * @param info
     * @return
     */
    public User getTeacherId(TrackInfo info) {
        // 面试官
        if (null != info.getTechnicalInterviewer()) {
            user.setUserId(Integer.parseInt(info.getTechnicalInterviewer()));
        }
        return user;
    }

    /**
     * 获取咨询师
     *
     * @param track
     * @return
     */
    public User getConsultId(Track track) {
        // 面试官
        if (null == track.getConsultId()) {
            track.getConsultId().setUserName("无");
        }
        return track.getConsultId();
    }

    /**
     * 获取面试官
     *
     * @param track
     * @return
     */
    public User getTeacherId(Track track) {
        // 面试官
        if (null == track.getTeacherId()) {
            track.getTeacherId().setUserName("无");
        }
        return track.getTeacherId();
    }
}
