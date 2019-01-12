package com.ss.crm.controller;

import com.ss.crm.entity.User;
import com.ss.crm.service.UserService;
import com.ss.crm.util.DESEncrypt;
import com.ss.crm.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //@RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上
    //该注解用于将Controller的方法返回的对象
    // ，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
    @PostMapping("userLogin")
    @ResponseBody
    public boolean findUser(@RequestParam String username, @RequestParam String password){
        System.out.println("username : "+username+"   "+"password : "+password );
        User user = userService.selectUser(username, DESEncrypt.encryptBasedDes(password));
        if(user!=null){
            System.out.println(user.getUserName().equals(username)&&DESEncrypt.decryptBasedDes(user.getPassword()).equals(password));
            return true;
        }else {
            return false;
        }
    }

    @PostMapping("findEmail")
    @ResponseBody
    public boolean findEmail(@RequestParam String email,@RequestParam String num){
        System.out.println("email:"+email);
        User user = userService.getEmail(email);
        if(user==null){
            try {
                SendEmail.send(email,num);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
        //发送邮件的内容
        @GetMapping("{email}/{num}/authCode")
        public String authCode(@PathVariable("email")String email, @PathVariable("num")String num, HttpServletRequest request){
            System.out.println("email:"+email+"num:"+num);
            if(num!=null){
                request.setAttribute("num",num);
                return "code";
            }
            return null;

        }
    @PostMapping("findName")
    @ResponseBody
    public boolean findName(@RequestParam String username){
        System.out.println("username:"+username);
        User user = userService.getUserName(username);
        if(user==null){
            return true;
        }
        return false;
    }

    @PostMapping("addUser")
    @ResponseBody
    public boolean addUser(@RequestParam String email,@RequestParam String username,@RequestParam String password){
        Integer user = userService.insertUser(email, username,DESEncrypt.encryptBasedDes(password));
        if(user>0){
            return true;
        }

        return false;
    }
}
