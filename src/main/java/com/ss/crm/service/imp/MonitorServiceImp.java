package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.User;
import com.ss.crm.mapper.MonitorMapper;
import com.ss.crm.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
