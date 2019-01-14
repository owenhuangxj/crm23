package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.mapper.StuMapper;
import com.ss.crm.service.StuService;
import com.ss.crm.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuServiceImp implements StuService {

    @Autowired
    private StuMapper sm;

    @Override
    public Integer addStuResume(Student stu) {
        //设置学号:当前时间
        stu.setStuNumber(DateUtil.getStuNmuber());
        // 性别：0：女，1：男
        switch (stu.getStuSex()) {
            case "女":
                stu.setStuSex("0");
                break;
            case "男":
                stu.setStuSex("1");
                break;
        }
        // 重要性：0：不重要，1：重要
        switch (stu.getStuImportance()) {
            case "不重要":
                stu.setStuImportance("0");
                break;
            case "重要":
                stu.setStuImportance("1");
                break;
        }
        //优先级别：1：无，2：低，3：中，4：高
        switch (stu.getStuLevel()) {
            case "无":
                stu.setStuLevel("1");
                break;
            case "低":
                stu.setStuLevel("2");
                break;
            case "中":
                stu.setStuLevel("3");
                break;
            case "高":
                stu.setStuLevel("4");
                break;
        }
        return sm.insertStuResume(stu);
    }

    @Override
    public Student getStuInfo(String stuNumber) {
        Student stu = sm.getStuInfoByStuNumber(stuNumber);
        switch (stu.getStuSex()) {
            case "0":
                stu.setStuSex("女");
                break;
            case "1":
                stu.setStuSex("男");
                break;
        }
        switch (stu.getStuImportance()) {
            case "0":
                stu.setStuImportance("不重要");
                break;
            case "1":
                stu.setStuImportance("重要");
                break;
        }
        //优先级别：1：无，2：低，3：中，4：高
        switch (stu.getStuLevel()) {
            case "1":
                stu.setStuLevel("无");
                break;
            case "2":
                stu.setStuLevel("低");
                break;
            case "3":
                stu.setStuLevel("中");
                break;
            case "4":
                stu.setStuLevel("高");
                break;
        }
        return stu;
    }

    @Override
    public Integer updateStuInfoByStuNumber(Student stu) {
        switch (stu.getStuLevel()) {
            case "无":
                stu.setStuLevel("1");
                break;
            case "低":
                stu.setStuLevel("2");
                break;
            case "中":
                stu.setStuLevel("3");
                break;
            case "高":
                stu.setStuLevel("4");
                break;
            default:
                stu.setStuLevel("1");
                break;
        }
        return sm.updateStuInfo(stu);
    }
}
