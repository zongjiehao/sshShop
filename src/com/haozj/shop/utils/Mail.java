package com.haozj.shop.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件发送工具类
 * 
 * @author 传智.郭嘉
 *
 */
public class Mail {
	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            :收件人
	 * @param code
	 *            :激活码
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 */
	public static void sendMail(String to) throws UnsupportedEncodingException, GeneralSecurityException {
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		//设置发送的smtp服务器
		
		//邮件发送协议
        props.setProperty("mail.transport.protocol", "smtp"); 
        //邮件发送服务器
        props.setProperty("mail.smtp.host", "smtp.qq.com"); 
        //邮件发送端口
        props.setProperty("mail.smtp.port", "465"); 
        //邮件发送认证
        props.setProperty("mail.smtp.auth", "true"); 
        //启用邮件发送调试模式
        props.setProperty("mail.debug","true"); 
        //开启ssl加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory.class", sf);
		Session session = Session.getInstance(props);
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("594796355@qq.com", "haozongjie@qq.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC 密送BCC
			// 设置标题
			message.setSubject("qq商城官方激活邮件");
			// 设置邮件正文:
			message.setContent("你好，连接成功,hahaha!","text/plain;charset=UTF-8");
			// 3.发送邮件:
			Transport ts=session.getTransport();
			ts.connect("594796355@qq.com", "rzcitsnozaimbcaj");
			ts.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			sendMail("18943023730@163.com");
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

