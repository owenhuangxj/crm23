package com.ss.crm.mapper;

import com.ss.crm.entity.Track;
import com.ss.crm.entity.TrackModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrackModelMapper {

    /**
     * 多条件查询学生跟踪信息
     * @param stuName 学生姓名
     * @param userName 咨询师姓名
     * @param trackWays 跟踪方式
     * @return 跟踪信息
     */
    List<TrackModel> getTrackModel(@Param("stuName") String stuName, @Param("userName") String userName,
                                    @Param("trackWays") String trackWays, @Param("start") Integer start,
                                    @Param("end") Integer end);

    /**
     * 获取查询出来的总条数
     * @param stuName
     * @param userName
     * @param trackWays
     * @return
     */
    Integer getTotal(@Param("stuName") String stuName, @Param("userName") String userName,
                     @Param("trackWays") String trackWays);


}
