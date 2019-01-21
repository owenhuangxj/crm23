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
    //测试
    @GetMapping("/echarts")
    public String leftMu2(){
        return "echarts";
    }
    //登陆页面
    @GetMapping("/")
    public String loginTest(){
        return "login";
    }
    //登陆成功跳转的页面
    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "admin";}
    @Autowired
    private UserService userService;
    //@RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上
    //该注解用于将Controller的方法返回的对象
    // ，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
    @PostMapping("userLogin")
    @ResponseBody
    public User findUser(@RequestParam String username, @RequestParam String password){
        //System.out.println("username : "+username+"   "+"password : "+password );
        User user = userService.selectUser(username, DESEncrypt.encryptBasedDes(password));
        if(user!=null){
            //System.out.println(user.getUserName().equals(username)&&DESEncrypt.decryptBasedDes(user.getPassword()).equals(password));
            user.setStatus(1);
            userService.ChangeStatus(user);
            return user;
        }else {
            return null;
        }
    }

    @PostMapping("findEmail")
    @ResponseBody
    //RequestParam 获取提交的参数
    public boolean findEmail(@RequestParam String email,@RequestParam String num){
        //System.out.println("email:"+email);
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
            //System.out.println("email:"+email+"num:"+num);
            if(num!=null){
                request.setAttribute("num",num);
                return "code";
            }
            return null;

        }
    @PostMapping("findName")
    @ResponseBody
    public boolean findName(@RequestParam String username){
        //System.out.println("username:"+username);
        User user = userService.getUserName(username);
        if(user==null){
            return true;
        }
        return false;
    }


    //　@responseBody注解的作用是将controller的方法返回的
    // 对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML
    @PostMapping("addUser")
    @ResponseBody
    public boolean addUser(@RequestParam String email,@RequestParam String username,@RequestParam String password){
        Integer user = userService.insertUser(email, username,DESEncrypt.encryptBasedDes(password));
        if(user>0){
            return true;
        }
        return false;
    }

    @PostMapping("changeState")
    @ResponseBody
     public boolean changeCondition(String username){
        Integer user = userService.updateStatus(username, 0);
        if(user>0){
            return true;
        }
        return false;
    }

}