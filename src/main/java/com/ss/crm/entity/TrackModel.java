package com.ss.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName TrackModel
 * @Description TODO
 * @Author Jack Hu
 * @Date 2019/1/17 18:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackModel implements Serializable {

    /**
     * 学元编号
     */
    private String stuNumber;
    /**
     * 学员名字
     */
    private String stuName;
    /**
     * 学员优先级
     */
    private String stuLevel;
    /**
     * 跟踪次数
     */
    private Integer trackCount;
    /**
     * 学员手机号
     */
    private String stuPhoneNum;
    /**
     * 学员来源
     */
    private String stuSource;
    /**
     * 进度情况
     */
    private String stuStatus;
    /**
     * 下次跟踪时间
     */
    private String trackNextTime;
    /**
     * 跟踪方式
     */
    private String trackWays;
    /**
     * 咨询师
     */
    private String userName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 预计上门时间
     */
    private String trackPredictTime;

    /*public TrackModel(String stuNumber, String stuName, String stuLevel, Integer trackCount, String stuPhoneNum, String stuSource, String stuStatus, String trackNextTime, String trackWays, String userName, String updateTime, String trackPredictTime) {
        this.stuNumber = stuNumber;
        this.stuName = stuName;
        this.stuLevel = stuLevel;
        this.trackCount = trackCount;
        this.stuPhoneNum = stuPhoneNum;
        this.stuSource = stuSource;
        this.stuStatus = stuStatus;
        this.trackNextTime = trackNextTime;
        this.trackWays = trackWays;
        this.userName = userName;
        this.updateTime = updateTime;
        this.trackPredictTime = trackPredictTime;
    }*/

    @Override
    public String toString() {
        return "TrackModel{" +
                "stuNumber=" + stuNumber +
                ", stuName='" + stuName + '\'' +
                ", stuLevel='" + stuLevel + '\'' +
                ", trackCount=" + trackCount +
                ", stuPhoneNum='" + stuPhoneNum + '\'' +
                ", stuSource='" + stuSource + '\'' +
                ", stuStatus=" + stuStatus +
                ", trackNextTime='" + trackNextTime + '\'' +
                ", trackWays='" + trackWays + '\'' +
                ", userName='" + userName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", trackPredictTime='" + trackPredictTime + '\'' +
                '}';
    }
}
