package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackInfo;
import com.ss.crm.entity.User;
import com.ss.crm.service.TrackInfoService;
import com.ss.crm.service.TrackService;
import com.ss.crm.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackInfoServiceImp implements TrackInfoService {

    @Autowired
    private TrackService ts;
    @Autowired
    private RedisCache<TrackInfo> cache;

    @Override
    public boolean addTrackInfoRecord(TrackInfo ti) {
//        cache.addCache(ti.getStuNumber(),ti);
//        // 咨询师
//        User user = new User();
//        user.setUserId(Integer.parseInt(ti.getTrailsman()));
//        // 面试官
//        User user1 = new User();
//        if(null!=ti.getTechnicalInterviewer()) {
//            user1.setUserId(Integer.parseInt(ti.getTechnicalInterviewer()));
//        }
//        System.out.println(user1.getUserName()+"------"+user1.getUserId());
        // 学生编号
        Student stu = new Student();
        stu.setStuNumber(ti.getStuNumber());
//        stu.setConsultId(user);
//        stu.setTeacherId(user1);
        // 获取对象
        Track track = new Track(ti.getTrackMethod(), ti.getPriority(), ti.getTrackTime(), ti.getCurrentStatus(), ti.getNextTrackTime(), ti.getTrackDuration(), ti.getPredictTime(), ti.getPredictTrade(), null, null, ti.getTrackDesc(), stu);
        return ts.addTrackRecord(track);
    }

    @Override
    public List<TrackInfo> getTrackInfo(String stuNumber) {
        List<TrackInfo> trackInfos = null;
        // 判断Redis中有没有数据
        if (cache.getCache(stuNumber).size() > 0) {
            return cache.getCache(stuNumber);
        } else {
            // 获取跟踪对象集合
            List<Track> tracks = ts.getTrackInfo(stuNumber);
            System.out.println("tracks---------->"+tracks);
            // 创建页面跟踪对象集合
            trackInfos = new ArrayList<TrackInfo>();
            // 没有数据数据时，返回null
            if(tracks.size()==0)
                return trackInfos;
            // 创建页面跟踪对象
            TrackInfo trackInfo = null;
            // 循环遍历
            for (Track track : tracks) {
                // 判断teacher是否为空
                if (track.getStuNumber().getTeacherId() == null) {
                    User user = new User();
                    user.setUserName("无");
                    track.getStuNumber().setTeacherId(user);
                }
                trackInfo = new TrackInfo(track.getTrackWays(), track.getTrackPriority(), track.getTrackTime(), track.getTrackStatus(), track.getTrackNextTime(), track.getTrackDuration(), track.getTrackPredictTime(), track.getTrackTurnoverTime(), null, null, track.getTrackDetails(), track.getStuNumber().getConsultId().getUserName(),track.getStuNumber().getStuNumber(),track.getStuNumber().getTeacherId().getUserName());
                trackInfos.add(trackInfo);
//                cache.addCache(stuNumber, trackInfo);
            }
        }
        return trackInfos;
    }
}
