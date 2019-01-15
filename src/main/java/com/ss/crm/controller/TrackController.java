package com.ss.crm.controller;

import com.ss.crm.entity.TrackInfo;
import com.ss.crm.service.TrackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/track")
public class TrackController {

    @Autowired
    private TrackInfoService tis;

    /**
     * 查询跟踪信息
     * @param stuNumber
     * @return 页面集合
     */
    @CrossOrigin
    @RequestMapping(value = "/getTrack", produces = "application/json;charset=UTF-8")
    public @ResponseBody List<TrackInfo> getTrackInfo(
            @RequestParam("stuNumber") String stuNumber) {
        return tis.getTrackInfo(stuNumber);
    }

    /**
     * 添加跟踪信息
     * @param trackInfo 跟踪信息页面数据
     * @return 是否添加成功
     */
    @RequestMapping(value = "addTrack", produces = "application/json;charset=UTF-8")
    public @ResponseBody  Boolean addTrackMessage(TrackInfo trackInfo){
        return tis.addTrackInfoRecord(trackInfo);
    }


}
