package com.ss.crm;

import com.ss.crm.util.ExcelToCsv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class csvTests {

    @Value("${uploadDir}")
    private  String uploadDir;

    @Test
    /*横向数据采集测试*/
    public void contextLoads() {
        ExcelToCsv csvUtil = new ExcelToCsv("C:/Users//Administrator/Desktop/爱你.xls","d://");
        List<String> list=new ArrayList<>();
        list.add("性别");
        list.add("姓名");
        list.add("出生日期");
        list.add("期望薪资");
       // List<Integer> indexList = csvUtil.getNeededColumnIndex(list, 0, 0);
        List<Integer> neededRowIndex = csvUtil.getNeededRowIndex(list, 1, 1);
        int num = csvUtil.getWorkbook().getNumberOfSheets();
        System.out.println(num);
        csvUtil.consolidation(1,num,neededRowIndex,2,2);
        csvUtil.close();
       // System.out.println(Arrays.deepToString(indexList.toArray()));
        System.out.println(Arrays.deepToString(neededRowIndex.toArray()));
    /*        csvUtil.excelToCSV(0, 0,null);*/
    /*    csvUtil.excelToCSV(0, 0,indexList);*/
    }

    @Test
    /*纵向数据采集测试*/
    public void columnTest() {
        ExcelToCsv csvUtil = new ExcelToCsv("C:/Users//Administrator/Desktop/爱你.xls","d://");
        List<String> list=new ArrayList<>();
        list.add("性别");
        list.add("姓名");
        list.add("出生日期");
        list.add("期望薪资");
        List<Integer> indexList = csvUtil.getNeededColumnIndex(list, 0, 0);
        int num = csvUtil.getWorkbook().getNumberOfSheets();
        System.out.println(num);
        csvUtil.close();
         System.out.println(Arrays.deepToString(indexList.toArray()));
                csvUtil.excelToCSV(0, 0,null);
            csvUtil.excelToCSV(0, 0,indexList);
    }


    @Test
    public void uselessTest(){
        System.out.println("===========");
        System.out.println(uploadDir);

        System.out.println("=========");
    }



}

