package com.ss.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_student")
public class Student extends Model<Student> {

    @TableId
    /**学生id*/
    private Long stuId;
    /**
     * 学生编号
     */
    private String stuNumber;
    /**
     * 创建时间
     */
    private String stuCreateTime;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 学生性别：0：女，1：男
     */
    private String stuSex;
    /**
     * 重要客户
     */
    private String stuImportance;
    /**
     * 手机号
     */
    private String stuPhoneNum;
    /**
     * QQ号
     */
    private String stuQq;
    /**
     * 优先级别：1：无，2：低，3：中，4：高
     */
    private String stuLevel;
    /**
     * 居住地址
     */
    private String stuAddress;
    /**
     * 学生渠道
     */
    private String stuChannel;
    /**
     * 学生来源
     */
    private String stuSource;
    /**
     * 自我评价
     */
    private String stuEvaluation;
    /**
     * 工作经历
     */
    private String stuWork;
    /**
     * 项目经验
     */
    private String stuProject;
    /**
     * 教育经历
     */
    private String stuEducation;
    /**
     * 培训经历
     */
    private String stuTrained;
    /**
     * 证书
     */
    private String stuCredentials;
    /**
     * 在校情况
     */
    private String stuPerformance;
    /**
     * 咨询师id
     */
    private User consultId;
    /**
     * 面试老师id
     */
    private User teacherId;
    /**
     * 学生状态：0：未分配，1：新增，2：跟踪中，3：待面试，4：面试未通过，5：面试通过，6：已缴未清，7：已缴费，8：入学，9：放弃入学，10：退学，11：已退费，12：放弃
     */
    private String stuStatus;

    public Student(String stuNumber, String stuName, String stuCreateTime, String stuSex, String stuImportance, String stuPhoneNum, String stuQq, String stuLevel, String stuAddress, String stuChannel, String stuSource, String stuEvaluation, String stuWork, String stuProject, String stuEducation, String stuTrained, String stuCredentials, String stuPerformance, User consultId, User teacherId) {
        this.stuNumber = stuNumber;
        this.stuName = stuName;
        this.stuCreateTime = stuCreateTime;
        this.stuSex = stuSex;
        this.stuImportance = stuImportance;
        this.stuPhoneNum = stuPhoneNum;
        this.stuQq = stuQq;
        this.stuLevel = stuLevel;
        this.stuAddress = stuAddress;
        this.stuChannel = stuChannel;
        this.stuSource = stuSource;
        this.stuEvaluation = stuEvaluation;
        this.stuWork = stuWork;
        this.stuProject = stuProject;
        this.stuEducation = stuEducation;
        this.stuTrained = stuTrained;
        this.stuCredentials = stuCredentials;
        this.stuPerformance = stuPerformance;
        this.consultId = consultId;
        this.teacherId = teacherId;
    }

    public Student(String stuNumber, String stuName, String stuSex, String stuImportance, String stuPhoneNum, String stuQq, String stuLevel, String stuAddress, String stuChannel, String stuSource, String stuEvaluation, String stuWork, String stuProject, String stuEducation, String stuTrained, String stuCredentials, String stuPerformance) {
        this.stuNumber = stuNumber;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuImportance = stuImportance;
        this.stuPhoneNum = stuPhoneNum;
        this.stuQq = stuQq;
        this.stuLevel = stuLevel;
        this.stuAddress = stuAddress;
        this.stuChannel = stuChannel;
        this.stuSource = stuSource;
        this.stuEvaluation = stuEvaluation;
        this.stuWork = stuWork;
        this.stuProject = stuProject;
        this.stuEducation = stuEducation;
        this.stuTrained = stuTrained;
        this.stuCredentials = stuCredentials;
        this.stuPerformance = stuPerformance;
    }
}
