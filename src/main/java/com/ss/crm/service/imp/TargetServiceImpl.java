package com.ss.crm.service.imp;

import com.ss.crm.mapper.TargetMapper;
import com.ss.crm.mapper.TrackMapper;
import com.ss.crm.service.TargetService;
import com.ss.crm.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService{
    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private TrackMapper trackMapper;

    @Override
    public ChartData selectFindTarget() {
        Integer monthTarget = targetMapper.targetGetMonthType();//月目标上门量
        Integer monthTrack = trackMapper.getMonthId();//月实际上门量
        Integer weekTarget = targetMapper.targetGetWeekType();//周目标上门量
        Integer weekTrack = trackMapper.getWeekId();//周实际上门量
        //System.out.println("我获得的数据是："+monthTarget+monthTrack+weekTarget+weekTrack);
        List<Integer> dataAlread = new ArrayList<>();
        List<Integer> dataNo = new ArrayList<>();
        dataAlread.add(weekTrack);
        dataAlread.add(monthTrack);

        dataNo.add(weekTarget);
        dataNo.add(monthTarget);

        ChartData chartData = ChartData.chartData(dataAlread, dataNo);
        return chartData;
    }

    @Override
    public ChartData findTargetCount() {
        Integer getTargetMonth = targetMapper.getTargetMonth();//月目标关单量
        Integer getTrackMonth = trackMapper.trackGetMonth();//月实际关单量
        Integer getTargetWeek = targetMapper.getTargetWeek();//周目标关单量
        Integer getTrackWeek = trackMapper.trackGetWeek();//周实际关单量
        List<Integer> dataAlread= new ArrayList<>();
        List<Integer> dataNo = new ArrayList<>();
        dataAlread.add(getTrackWeek);
        dataAlread.add(getTrackMonth);
        dataNo.add(getTargetWeek);
        dataNo.add(getTargetMonth);
        ChartData targetData = ChartData.chartData(dataAlread, dataNo);
        return targetData;
    }

}
