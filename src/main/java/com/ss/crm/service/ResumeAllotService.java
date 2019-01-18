package com.ss.crm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 王顺坤
 * @Date: 2019/1/17 15:00
 * @Description:
 */
public class ResumeAllotService {

    public  static List<String> CONSUME_NEEDED_COLUMN=new ArrayList<>( Arrays.asList("姓名","性别","手机","出生日期","家庭地址","职能/职位","自我评价","工作经验","项目经验","培训经历"));
      public  static List<Integer> CONSUME_51JOB_ROW_INDEX=new ArrayList<>(Arrays.asList(9,10,11,13,22,35,39,41,43,52));
    /*public  static List<Integer> CONSUME_51JOB_ROW_INDEX=new ArrayList<>(Arrays.asList(9,10,11,13));*/
    public static List<Integer> CONSUME_51JOB_COLUMN_INDEX=new ArrayList<>(Arrays.asList(0,7,8));

}
