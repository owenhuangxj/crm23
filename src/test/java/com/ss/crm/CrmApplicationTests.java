package com.ss.crm;

import com.ss.crm.entity.Student;
import com.ss.crm.entity.Track;
import com.ss.crm.entity.User;
import com.ss.crm.service.StuService;
import com.ss.crm.service.TrackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    @Autowired
    private StuService ss;
    @Autowired
    private TrackService ts;

    @Test
    public void contextLoads() {
    }

    @Test
    public void stuTest(){
//        Student stu = ss.getStuInfo("20191121800");
//        Student stu = ss.getStuInfo("20191141148");
//        System.out.println(stu);
//        ss.addStuResume(new Student("20191141148","胡杰","男","重要","13421452356","651234568","高","河南","智联招聘","无","无","无","无","无","无","无","无"));
    }

    @Test
    public void trackTest(){
        List<Track> info = ts.getTrackInfo("20191121800");
        System.out.println(info);


//        User user = new User();
//        user.setUserId(5);
//
//        Student stu = new Student();
//        stu.setStuNumber("20191141148");
//        stu.setStuLevel("高");
//
//        String trackTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        String trackPredictTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        String trackTurnoverTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//
//        Track track = new Track("微信",trackTime,"跟踪中",trackTime,30,trackPredictTime,trackTurnoverTime,"重要","有效","无");
//        track.setConsultId(user);
//        track.setStuNumber(stu);
//
//        System.out.println(track);
//
//        ts.addTrackRecord(track);
    }


}

