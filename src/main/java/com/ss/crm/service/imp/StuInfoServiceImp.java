package com.ss.crm.service.imp;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.StudentInfo;
import com.ss.crm.entity.User;
import com.ss.crm.service.StuInfoService;
import com.ss.crm.service.StuService;
import com.ss.crm.util.DateUtil;
import com.ss.crm.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuInfoServiceImp implements StuInfoService {

    private User user = new User();
    @Autowired
    private StuService ss;
    @Autowired
    private RedisCache<StudentInfo> cache;

    @Override
    public Boolean addStuResume(StudentInfo info) {
        return ss.addStuResume(getStu(info)) > 0;
    }

    @Override
    public StudentInfo getStuInfo(String stuNumber) {
        StudentInfo stuInfo = null;
        if (cache.getCache("stu" + stuNumber).size() > 0) {
            return (StudentInfo) cache.getCache("stu"+stuNumber).get(0);
        } else {
            Student info = ss.getStuInfo(stuNumber);
            stuInfo = new StudentInfo(info.getStuNumber(), info.getStuName(), info.getStuSex(), info.getStuImportance(), info.getStuPhoneNum(), info.getStuQq(), info.getStuLevel(), info.getStuAddress(), info.getStuChannel(), info.getStuSource(), info.getStuEvaluation(), info.getStuWork(), info.getStuProject(), info.getStuEducation(), info.getStuTrained(), info.getStuCredentials(), info.getStuPerformance(), getConsultId(info).getUserId(), getTeacherId(info).getUserId());
            // 添加缓存
//            cache.addCache("stu"+stuNumber,stuInfo);
            return stuInfo;
        }
    }

    @Override
    public Integer updateStuInfo(StudentInfo info) {
        Integer integer = ss.updateStuInfoByStuNumber(getStu(info));
        if (integer > 0) {
            cache.updateCache("stu" + info.getStuNumber());
        }
        return integer;
    }

    /**
     * 获取咨询师
     *
     * @param info
     * @return
     */
    public User getConsultId(StudentInfo info) {
        // 咨询师
        if (null != info.getTeacherId()) {
            user.setUserId(info.getConsultId());
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
        if (null != info.getTeacherId()) {
            user.setUserId(info.getTeacherId());
        }
        return user;
    }

    /**
     * 获取咨询师
     *
     * @param stu
     * @return
     */
    public User getConsultId(Student stu) {
        // 咨询师
        if (null == stu.getConsultId()) {
            user.setUserId(0);
            stu.setConsultId(user);
        }
        return stu.getConsultId();
    }

    /**
     * 获取面试官
     *
     * @param stu
     * @return
     */
    public User getTeacherId(Student stu) {
        // 面试官
        if (null == stu.getTeacherId()) {
            user.setUserId(0);
            stu.setTeacherId(user);
        }
        return stu.getTeacherId();
    }

    /**
     * 获取student对象
     *
     * @param info
     * @return
     */
    public Student getStu(StudentInfo info) {
        return new Student(info.getStuNumber(), DateUtil.getDateTime(), info.getStuName(), info.getStuSex(), info.getStuImportance(), info.getStuPhoneNum(), info.getStuQq(), info.getStuLevel(), info.getStuAddress(), info.getStuChannel(), info.getStuSource(), info.getStuEvaluation(), info.getStuWork(), info.getStuProject(), info.getStuEducation(), info.getStuTrained(), info.getStuCredentials(), info.getStuPerformance(), getConsultId(info), getTeacherId(info));
    }
}
