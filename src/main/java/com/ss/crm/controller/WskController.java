package com.ss.crm.controller;
/**
 * @Author: 王顺坤
 * @Date: 2019年1月11日
 * @Description:王顺坤的前端测试类
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class WskController {
    @GetMapping("/test")
    public String index() {

        return "wsk-test";
    }
    @RequestMapping("upload")

    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest req){
        String upDirRealPath = req.getServletContext().getRealPath("/uploadDir/");
        String msg = null;
        Boolean state = null;
        Integer value = null;
        // 从session中获取操作者
        // 上传到指定路径
        for(MultipartFile file : files){
            String src = upDirRealPath.concat(file.getOriginalFilename());
            try {
                // 上传
                file.transferTo(new File(src));
                // 调用service中的方法


            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return "index";
    }


}
