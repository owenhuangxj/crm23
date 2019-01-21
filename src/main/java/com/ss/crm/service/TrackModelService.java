package com.ss.crm.service;

import com.ss.crm.entity.PageData;
import com.ss.crm.entity.TrackModel;

public interface TrackModelService {

    /**
     * 查询学员跟踪信息
     * @param input    用户输入的信息
     * @param value    用户选择了通过什么来查询，例如：姓名、咨询师...
     * @param pageNum  分页参数：当前页
     * @param pageSize 分页参数：一页显示多少条
     * @return 返回前端需要的指定格式的数据
     */
    PageData<TrackModel> getTrackModel(String input, String value, Integer pageNum, Integer pageSize);


}
