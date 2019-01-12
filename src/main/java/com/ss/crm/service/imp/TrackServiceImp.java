package com.ss.crm.service.imp;

import com.ss.crm.entity.Track;
import com.ss.crm.mapper.TrackMapper;
import com.ss.crm.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImp implements TrackService {

    @Autowired
    private TrackMapper tm;

    @Override
    public boolean addTrackRecord(Track track) {
        switch (track.getTrackStatus()) {
            case "新增":
                track.setTrackStatus("1");
                break;
            case "跟踪中":
                track.setTrackStatus("2");
                break;
            case "待面试":
                track.setTrackStatus("3");
                break;
            case "面试未通过":
                track.setTrackStatus("4");
                break;
            case "面试通过":
                track.setTrackStatus("5");
                break;
            case "已缴未清":
                track.setTrackStatus("6");
                break;
            case "已缴费":
                track.setTrackStatus("7");
                break;
            case "入学":
                track.setTrackStatus("8");
                break;
            case "放弃入学":
                track.setTrackStatus("9");
                break;
            case "退学":
                track.setTrackStatus("10");
                break;
            case "放弃":
                track.setTrackStatus("11");
                break;
            default:
                track.setTrackStatus("1");
                break;
        }
        if (tm.insertTrackRecord(track) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Track> getTrackInfo(String stuNumber) {
        // 获取数据
        List<Track> tracks = tm.getTrackInfo(stuNumber);
        for (Track track : tracks) {
            switch (track.getTrackStatus()) {
                case "1":
                    track.setTrackStatus("新增");
                    break;
                case "2":
                    track.setTrackStatus("跟踪中");
                    break;
                case "3":
                    track.setTrackStatus("待面试");
                    break;
                case "4":
                    track.setTrackStatus("面试未通过");
                    break;
                case "5":
                    track.setTrackStatus("面试通过");
                    break;
                case "6":
                    track.setTrackStatus("已缴未清");
                    break;
                case "7":
                    track.setTrackStatus("已缴费");
                    break;
                case "8":
                    track.setTrackStatus("入学");
                    break;
                case "9":
                    track.setTrackStatus("放弃入学");
                    break;
                case "10":
                    track.setTrackStatus("退学");
                    break;
                case "11":
                    track.setTrackStatus("放弃");
                    break;
            }
        }
        return tracks;
    }


}
