package com.ss.crm;

import com.ss.crm.entity.PageData;
import com.ss.crm.entity.TrackModel;
import com.ss.crm.service.TrackModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @ClassName TrackTest
 * @Description TODO
 * @Author Jack Hu
 * @Date 2019/1/18 14:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackTest {

    @Autowired
    private TrackModelService ts;

    @Test
    public void test(){
        PageData<TrackModel> trackModel = ts.getTrackModel(null, "1", 1, 10);
        System.out.println("trackModel：" + trackModel);
    }




}
