package com.ss.crm;

import com.ss.crm.util.ExcelToCsv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class csvTests {


    @Test
    public void contextLoads() {
        ExcelToCsv csvUtil = new ExcelToCsv("C:/Users//Administrator/Desktop/爱你.xls","d://");
        List<String> list=new ArrayList<>();
        list.add("性别");
        list.add("姓名");
        list.add("出生日期");
        List<Integer> indexList = csvUtil.getNeededColumnIndex(list, 0, 0);
        String s = Arrays.deepToString(indexList.toArray());
        System.out.println(s);
        csvUtil.excelToCSV(0, 0,null);
        csvUtil.excelToCSV(0, 0,indexList);
    }




}

