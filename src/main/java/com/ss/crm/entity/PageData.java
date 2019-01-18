package com.ss.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageData
 * @Description TODO
 * @Author Jack Hu
 * @Date 2019/1/17 17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {

    /**
     * 存对象的集合
     */
    private List<T> rows;
    /**
     * 总行数
     */
    private Integer total;
}
