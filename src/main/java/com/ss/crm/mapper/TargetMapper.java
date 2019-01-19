package com.ss.crm.mapper;


import java.util.Map;

public  interface TargetMapper {
     /*查询本月的上门量*/
   Integer targetGetMonthType() ;

     //查询本周的上门量
     Integer targetGetWeekType();
    /*查询本月的下单量*/
    Integer getTargetMonth();

    /*查询本周的下单量*/
    Integer getTargetWeek();
}
