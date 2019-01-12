package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.mapper.StuMapper;
import com.ss.crm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StuServiceImp implements StuService {

    @Autowired
    private StuMapper sm;

    @Override
    public Integer addStuResume(Student stu) {
        String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        stu.setStuNumber("");
        return sm.insertStuResume(stu);
    }

    @Override
    public Student getStuInfo(String stuNumber) {

        return sm.getStuInfo(stuNumber);
    }
}
