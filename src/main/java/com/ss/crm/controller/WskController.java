package com.ss.crm.controller;
/**
 * @Author: 王顺坤
 * @Date: 2019年1月11日
 * @Description:王顺坤的前端测试类
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class WskController {
    @Value("${uploadDir}")
    private  String uploadDir;
    @GetMapping("/test")
    public String index() {

        return "wsk-test";
    }
    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){

        for(MultipartFile file:files){
            try {
                System.out.println(uploadDir);
                System.out.println(file.getOriginalFilename());
                file.transferTo(new File(uploadDir.concat(file.getOriginalFilename())));
                System.out.println(uploadDir.concat(file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "wsk-test";
    }


}
