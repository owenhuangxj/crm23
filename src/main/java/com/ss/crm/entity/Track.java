package com.ss.crm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_track")
public class Track extends Model<Track> {

    /**跟踪方式*/
    private String trackWays;
    /**跟踪时间*/
    private String trackTime;
    /**跟踪状态/进度情况 1：新增，2：跟踪中，3：待面试，4：面试未通过，5：面试通过，6：已缴未清，7：已缴费，8：入学，9：放弃入学，10：退学，11：放弃*/
    private String trackStatus;
    /**下次跟踪时间*/
    private String trackNextTime;
    /**跟踪时长*/
    private Integer trackDuration;
    /**预计成交时间*/
    private String trackTurnoverTime;
    /**重要程度*/
    private String trackImportance;
    /**是否有效*/
    private String trackValid;
    /**跟踪内容*/
    private String trackDetails;
    /**咨询师id*/
    private Employee consultId;
    /**学生id*/
    private Student stuId;
    /**面试老师id*/
    private Employee teacherId;

}
