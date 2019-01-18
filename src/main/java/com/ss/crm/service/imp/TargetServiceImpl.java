package com.ss.crm.service.imp;

import com.ss.crm.mapper.TargetMapper;
import com.ss.crm.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements TargetService{
    @Autowired
    private TargetMapper targetMapper;


    @Override
    public Integer selectTargetMonthId() {
        return targetMapper.targetGetMonthType();
    }

    @Override
    public Integer selectTargetDayId() {
        return targetMapper.targetGetDayType();
    }
}
