package com.ss.crm.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

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
    private Integer newTrackDuration;
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

    public String getTrackMethod() {
        return trackMethod;
    }

    public String getPriority() {
        return priority;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getNextTrackTime() {
        return nextTrackTime;
    }

    public Integer getNewTrackDuration() {
        return newTrackDuration;
    }

    public String getPredictTime() {
        return predictTime;
    }

    public String getPredictTrade() {
        return predictTrade;
    }

    public String getTrackDesc() {
        return trackDesc;
    }

    public String getTrailsman() {
        return trailsman;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public String getTechnicalInterviewer() {
        return technicalInterviewer;
    }

    public void setTrackMethod(String trackMethod) {
        this.trackMethod = trackMethod;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setNextTrackTime(String nextTrackTime) {
        this.nextTrackTime = nextTrackTime;
    }

    public void setNewTrackDuration(Integer newTrackDuration) {
        this.newTrackDuration = newTrackDuration;
    }

    public void setPredictTime(String predictTime) {
        this.predictTime = predictTime;
    }

    public void setPredictTrade(String predictTrade) {
        this.predictTrade = predictTrade;
    }

    public void setTrackDesc(String trackDesc) {
        this.trackDesc = trackDesc;
    }

    public void setTrailsman(String trailsman) {
        this.trailsman = trailsman;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public void setTechnicalInterviewer(String technicalInterviewer) {
        this.technicalInterviewer = technicalInterviewer;
    }

    public TrackInfo(String trackMethod, String priority, String trackTime, String currentStatus, String nextTrackTime, Integer newTrackDuration, String predictTime, String predictTrade, String trackDesc, String trailsman, String stuNumber, String technicalInterviewer) {
        this.trackMethod = trackMethod;
        this.priority = priority;
        this.trackTime = trackTime;
        this.currentStatus = currentStatus;
        this.nextTrackTime = nextTrackTime;
        this.newTrackDuration = newTrackDuration;
        this.predictTime = predictTime;
        this.predictTrade = predictTrade;
        this.trackDesc = trackDesc;
        this.trailsman = trailsman;
        this.stuNumber = stuNumber;
        this.technicalInterviewer = technicalInterviewer;
    }
}
