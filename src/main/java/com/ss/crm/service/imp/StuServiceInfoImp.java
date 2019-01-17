package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.StudentInfo;
import com.ss.crm.entity.User;
import com.ss.crm.service.StuInfoService;
import com.ss.crm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuServiceInfoImp implements StuInfoService {

    @Autowired
    private StuService ss;

    @Override
    public Boolean addStuResume(StudentInfo info) {
        User user1 = getConsultId(info);
        System.out.println(user1.getUserName() + "------" + user1.getUserId());
        return ss.addStuResume(getStu(info)) > 0;
    }

    @Override
    public StudentInfo getStuInfo(String stuNumber) {
        Student info = ss.getStuInfo(stuNumber);
        System.out.println(info);
        return new StudentInfo(info.getStuNumber(), info.getStuName(), info.getStuSex(), info.getStuImportance(), info.getStuPhoneNum(), info.getStuQq(), info.getStuLevel(), info.getStuAddress(), info.getStuChannel(), info.getStuSource(), info.getStuEvaluation(), info.getStuWork(), info.getStuProject(), info.getStuEducation(), info.getStuTrained(), info.getStuCredentials(), info.getStuPerformance());
    }

    @Override
    public Integer updateStuInfo(StudentInfo info) {
        return ss.updateStuInfoByStuNumber(getStu(info));
    }

    /**
     * 获取咨询师
     *
     * @param info
     * @return
     */
    public User getConsultId(StudentInfo info) {
        // 咨询师
        User user = new User();
        if (null != info.getTeacherId()) {
            user.setUserId(Integer.parseInt(info.getConsultId()));
        }
        return user;
    }

    /**
     * 获取面试官
     *
     * @param info
     * @return
     */
    public User getTeacherId(StudentInfo info) {
        // 面试官
        User user = new User();
        if (null != info.getTeacherId()) {
            user.setUserId(Integer.parseInt(info.getTeacherId()));
        }
        return user;
    }

    /**
     * 获取student对象
     *
     * @param info
     * @return
     */
    public Student getStu(StudentInfo info) {
        return new Student(info.getStuNumber(), info.getStuName(), info.getStuSex(), info.getStuImportance(), info.getStuPhoneNum(), info.getStuQq(), info.getStuLevel(), info.getStuAddress(), info.getStuChannel(), info.getStuSource(), info.getStuEvaluation(), info.getStuWork(), info.getStuProject(), info.getStuEducation(), info.getStuTrained(), info.getStuCredentials(), info.getStuPerformance(), getConsultId(info), getTeacherId(info));
    }
}
