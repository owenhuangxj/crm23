package com.ss.crm.service;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.StudentInfo;

public interface StuInfoService {

    Boolean addStuResume(StudentInfo info);

    StudentInfo getStuInfo(String stuNumber);

    Integer updateStuInfo(StudentInfo info);

}

