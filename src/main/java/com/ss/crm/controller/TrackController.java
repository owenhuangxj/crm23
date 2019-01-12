package com.ss.crm.controller;

import com.ss.crm.entity.Track;
import com.ss.crm.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/track")
public class TrackController {

    @Autowired
    private TrackService ts;

    /**
     * 查询跟踪信息
     * @param stuNumber
     * @return 页面集合
     */
    @RequestMapping(value = "/getTrack", produces = "application/json;charset=UTF-8")
    public @ResponseBody List<Track> getTrackInfo(
            @RequestParam("stuNumber") String stuNumber) {
        return ts.getTrackInfo(stuNumber);
    }

    /**
     * 添加跟踪信息
     * @param track 跟踪信息数据
     * @return 是否添加成功
     */
    @RequestMapping(value = "addTrack", produces = "application/json;charset=UTF-8")
    public @ResponseBody  Boolean addTrackMessage(Track track){
        return ts.addTrackRecord(track);
    }


}
