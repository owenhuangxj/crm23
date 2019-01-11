package com.ss.crm.controller;
/**
 * @Author: 王顺坤
 * @Date: 2019年1月11日
 * @Description:王顺坤的前端测试类
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WskController {
    @GetMapping("/test")
    public String index() {

        return "wsk-test";
    }

}
