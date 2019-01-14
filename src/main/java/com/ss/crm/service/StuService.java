package com.ss.crm.service;

import com.ss.crm.entity.Student;

public interface StuService {

    /**
     * 添加学生简历
     * @param student 学生数据
     * @return 改变行数
     */
    Integer addStuResume(Student student);

    /**
     * 通过编号查询学生信息
     * @param stuNumber 学生编号
     * @return 学生信息
     */
    Student getStuInfo( String stuNumber);

    /**
     * 修改学生信息
     * @param stu 学生数据
     * @return 改变行数
     */
    Integer updateStuInfoByStuNumber(Student stu);


}
