package com.ss.crm;

import com.ss.crm.entity.Student;
import com.ss.crm.service.StuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    @Autowired
    private StuService ss;

    @Test
    public void contextLoads() {
    }

    @Test
    public void stuTest(){
        Student stu = ss.getStuInfo("123");
        System.out.println(stu);
    }

    @Test
    public void trackTest(){

    }


}

