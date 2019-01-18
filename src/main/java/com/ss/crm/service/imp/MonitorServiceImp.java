package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.User;
import com.ss.crm.mapper.MonitorMapper;
import com.ss.crm.service.MonitorService;
import com.ss.crm.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MonitorServiceImp implements MonitorService {
    @Autowired
    private MonitorMapper monitorMapper;
    @Override
    public List<User> selectAllWorktor(Integer currentPage, Integer currentPageSize) {
        System.out.println("start:"+currentPage+" currentPageSize:"+currentPageSize );
        return monitorMapper.selectAllWorktor((currentPage-1)*currentPageSize,currentPageSize);
    }

    @Override
    public Integer selectWorktorNum() {
        return monitorMapper.selectWorktorNum();
    }

    @Override
    public List<Student> selectWorktorOfStu(Integer userId, Integer currentPage, Integer currentPageSize) {
        return monitorMapper.selectWorktorOfStu(userId,(currentPage-1)*currentPageSize,currentPageSize);
    }

    @Override
    public ChartData selectWorkOfBusiness(Integer userId) {
        Integer  alreadZhouWord=  monitorMapper.selectAlreadZhouWork(userId);//查询周完成的工作量
        Integer  noZhouWord=   monitorMapper.selectNoZhouWork(userId);//查询周未完成的工作量
        Integer  alreadYueWord=    monitorMapper.selectAlreadYueWork(userId);//查询周完成的工作量
        Integer  NoYueWord=  monitorMapper.selectNoYueWork(userId);//查询周未完成的工作量
       List<Integer> dataAlread = new ArrayList<>();
        List<Integer> dataNo = new ArrayList<>();
        dataAlread.add(alreadYueWord);
        dataAlread.add(alreadZhouWord);
        dataNo.add(noZhouWord);
        dataNo.add(noZhouWord);
        ChartData.chartData(dataAlread,dataNo);
        return null;
    }
}
