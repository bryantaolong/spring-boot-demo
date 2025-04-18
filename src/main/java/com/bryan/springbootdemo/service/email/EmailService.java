package com.bryan.springbootdemo.service.email;

/**
 * ClassName: EmailService
 * Package: com.bryan.toyouth.service
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/18 - 14:55
 * Version: v1.0
 */
public interface EmailService {
    void sendEmail(String to, String subject, String content) throws Exception;
}
