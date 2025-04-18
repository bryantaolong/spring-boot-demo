package com.bryan.springbootdemo.service.email.impl;

import com.bryan.springbootdemo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 类名: SpringEmailService
 * 包名: com.bryan.springbootdemo.service.email.impl
 * 描述: 基于 Spring 的电子邮件发送服务实现，提供通过 SMTP 发送电子邮件的功能。
 * 作者: Bryan Long
 * 创建时间: 2024/12/18 - 14:57
 * 版本: v1.0
 */
@Service
@RequiredArgsConstructor
public class SpringEmailService implements EmailService {

    private final JavaMailSender javaMailSender;  // Spring 提供的邮件发送器

    /**
     * 发送简单的电子邮件
     *
     * @param to       收件人的电子邮件地址
     * @param subject  邮件的主题
     * @param content  邮件的内容
     */
    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();  // 创建简单的邮件消息
        message.setFrom("bryanlongshelby@outlook.com");        // 设置发件人
        message.setTo(to);                                    // 设置收件人
        message.setSubject(subject);                          // 设置邮件主题
        message.setText(content);                             // 设置邮件内容

        javaMailSender.send(message); // 通过 JavaMailSender 发送邮件
    }
}
