package com.ss.crm;

import com.ss.crm.entity.AllotLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void select(){
        AllotLog allotLog = new AllotLog();
        List<AllotLog> allotLogs = allotLog.selectAll();
        System.out.println(allotLogs);
    }


}

