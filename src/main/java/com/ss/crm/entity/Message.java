
package com.ss.crm.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * 消息实体类
 */
public class Message {
    private Integer id;
    /**
     * 发送者的id
     */
    private int msgFrom;
    /**
     * 接收者的id
     */
    private int msgTo;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String text;
    /**
     * 状态。
     * 0 未读
     * 1 已读
     * 2 已删
     */
    private Integer status;
    /**
     * 时间戳
     */
    private String timeStap;

}
