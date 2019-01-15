package com.ss.crm.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackInfo extends Model<TrackInfo> {

    /**
     * 跟踪方式
     */
    private String trackMethod;
    /**
     * 优先级：1：无，2：低，3：中，4：高
     */
    private String priority;
    /**
     * 跟踪时间
     */
    private String trackTime;
    /**
     * 跟踪状态/进度情况 1：新增，2：跟踪中，3：待面试，4：面试未通过，5：面试通过，6：已缴未清，7：已缴费，8：入学，9：放弃入学，10：退学，11：放弃
     */
    private String currentStatus;
    /**
     * 下次跟踪时间
     */
    private String nextTrackTime;
    /**
     * 跟踪时长
     */
    private Integer trackDuration;
    /**
     * 预计上门时间
     */
    private String predictTime;
    /**
     * 预计成交时间
     */
    private String predictTrade;
//    /**重要程度*/
//    private String trackImportance;
//    /**是否有效*/
//    private String trackValid;
    /**
     * 跟踪内容/备注
     */
    private String trackDesc;
    /**
     * 咨询师id
     */
    private String trailsman;
    /**
     * 学生id
     */
    private String stuNumber;
    /**
     * 面试老师id
     */
    private String technicalInterviewer;

}
