package com.ss.crm.service.imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackModel;
import com.ss.crm.mapper.TrackMapper;
import com.ss.crm.mapper.TrackModelMapper;
import com.ss.crm.service.TrackModelService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<TrackModel> getTrackModel(String stuName, String userName, String trackWays,Integer pageNum,Integer pageSize) {
        Integer start = (pageNum - 1) * pageSize;
        List<TrackModel> trackModels = new ArrayList<>();
        List<Track> track = tmm.getTrackModel(stuName, userName, trackWays, start, pageSize);
        for (Track t : track) {
//            Integer trackCount = tm.getTrackCount(t.getStuNumber().getStuNumber());
            trackModels.add(new TrackModel(t.getStuNumber().getStuNumber(),t.getStuNumber().getStuName(),t.getStuNumber().getStuLevel(),
                    5,t.getStuNumber().getStuPhoneNum(),t.getStuNumber().getStuSource(),t.getStuNumber().getStuStatus(),
                    t.getTrackNextTime(),t.getTrackWays(),t.getStuNumber().getConsultId().getUserName(),t.getUpdateTime(),t.getTrackPredictTime()));
        }
        return trackModels;
    }


    /*public List<Track> getTrack(Integer pageNum,Integer pageSize){
        Track track = new Track();
        Page<Track> page = new Page<Track>(pageNum,pageSize);
        track.selectPage(page,)
        return null;
    }*/



}
