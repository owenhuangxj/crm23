package com.ss.crm.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * javax.mail.AuthenticationFailedException: 535 authentication failed错误解决办法
 * 调用163邮箱服务器来发送邮件需要开启POP3/SMTP服务,开启163邮件时会让我们设置客户端授权码，这个授权码替代邮箱password即可成功发送邮件
 * @author DELL
 *
 */
public class SendEmail {
	//发件人地址
    public static String senderAddress ="13228228841@163.com";
    //发件人账户名
    public static String senderAccount = "13228228841@163.com";
    //发件人账户密码(这里的密码是授权码，不是邮箱密码)
    public static String senderPassword = "aa12345678";

    public static void send(String recipientAddress,String code) throws Exception {
        //1、连接邮件服务器的参数配置
        Properties props = new Properties();
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.163.com");
        //2、创建定义整个应用程序所需的环境信息的 Session 对象 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session,recipientAddress,code);
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        //设置发件人的账户名和密码
        transport.connect(senderAccount, senderPassword);
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg,msg.getAllRecipients());

        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

        //5、关闭邮件连接
        transport.close();
    }

    /**
     * 获得创建一封邮件的实例对象
     * @param session
     * @return
     * @throws MessagingException
     * @throws AddressException
     */
    public static MimeMessage getMimeMessage(Session session,String recipientAddress,String code) throws Exception{
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(senderAddress));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
//        InternetAddress [] toAddr = new InternetAddress[2];
//        toAddr[0] = new InternetAddress(senderAddress);//自己的邮箱
//        toAddr[1] = new InternetAddress(recipientAddress);//对方的邮箱
        //  To: 收件人（可以增加多个收件人、抄送、密送）
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(recipientAddress));
        msg.setRecipient(MimeMessage.RecipientType.CC,new InternetAddress(senderAddress));
        //设置邮件主题
        msg.setSubject("来自用户的验证邮件","UTF-8");
        //设置邮件正文
        String email="<h1><a href='"+ UserConstant.APPSERVERADDRESS+"/"+recipientAddress+"/"+code+"/authCode'>"+ UserConstant.APPSERVERADDRESS+"/"+recipientAddress+"/num/authCode</a>接收验证码<h1>";
        msg.setContent(email, "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
         // 7. 保存设置
		msg.saveChanges();
        return msg;
    }

//    public static void main(String[] args) throws Exception {
//			send("2535545766@qq.com", "123456");
//
//	}
}


