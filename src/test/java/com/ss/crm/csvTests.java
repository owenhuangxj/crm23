package com.ss.crm;

import com.ss.crm.mapper.ResumeAllotMapper;
import com.ss.crm.service.ResumeAllotService;
import com.ss.crm.util.ExcelToCsv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ResumeAllotMapper ram;
    @Test
    /*横向数据采集测试*/
    public void rowTest() {
        ExcelToCsv csvUtil = new ExcelToCsv();
        csvUtil.setSrc("C:/Users//Administrator/Desktop/爱你.xls");
        csvUtil.setTargetFolder("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/","n8dsaS");
        int num = csvUtil.getWorkbook().getNumberOfSheets();
        final List<Integer> index = ResumeAllotService.CONSUME_51JOB_ROW_INDEX;
        csvUtil.setSexIndex(1);
        csvUtil.setColumnIndexList(2,2);
        csvUtil.setAppendInfo(new ArrayList<>(Arrays.asList("Boos直聘","简历导入")));
        final String src = csvUtil.consolidation(1, num, index);
        ram.ImportResume(src);
      /*  csvUtil.close();*/
     //   System.out.println(Arrays.deepToString(neededRowIndex.toArray()));
    }
    @Test
    public void rowsIndexTest() {

    }


    @Test
    /*纵向数据采集测试*/
    public void columnTest() {
        ExcelToCsv csvUtil = new ExcelToCsv();
        csvUtil.setSrc("C:/Users//Administrator/Desktop/爱你.xls");
        csvUtil.setTargetFolder("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/","列采集");
        csvUtil.setAppendInfo(new ArrayList<>(Arrays.asList("51job","简历录入")));
        final List<Integer> index = ResumeAllotService.CONSUME_51JOB_COLUMN_INDEX;
        csvUtil.setColumnIndexList(index);
        csvUtil.setSexIndex(1);
        csvUtil.excelToCSV(2,0);
    }


    @Test
    public void uselessTest(){
        ram.ImportResume("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/test.csv");
    }



}

