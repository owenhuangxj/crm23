package com.ss.crm.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//本类包装的是员工业务数据的柱状图
public class ChartData extends HashMap {

        public static ChartData chartData(List<Integer>AlreadData,List<Integer>NoData){
            ChartData chartData = new ChartData();
              List  category=   new ArrayList<String>();
              category.add("本周");
              category.add("本月");
            chartData.put("category",category);//柱状图的下坐标
            chartData.put("AlreadData",AlreadData);//对应的数据
            chartData.put("NoData",NoData);
            return chartData;
        }


}
