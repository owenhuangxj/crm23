package com.ss.crm.mapper;


public  interface TargetMapper {
     /*查询本月的数据*/
     Integer targetGetMonthType() ;

     //查询本周的数据
     Integer targetGetDayType();
}
