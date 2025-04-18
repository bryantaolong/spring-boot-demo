package com.bryan.springbootdemo.controller.email;

import com.bryan.springbootdemo.service.email.EmailService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: SpringEmailController
 * 包名: com.bryan.springbootdemo.controller.email
 * 描述: 控制器类，用于处理电子邮件发送请求。
 * 作者: Bryan Long
 * 创建时间: 2024/12/18 - 14:55
 * 版本: v1.0
 */
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class SpringEmailController {

    // 注入的电子邮件服务，用于发送电子邮件
    private final EmailService springEmailService;

    /**
     * 处理发送电子邮件的请求。
     *
     * @param to      收件人电子邮件地址
     * @param subject 电子邮件主题
     * @param content 电子邮件内容
     * @return 响应实体，包含操作结果的消息或错误信息
     */
    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendEmail(
            @RequestParam @NotNull String to,
            @RequestParam @NotNull String subject,
            @RequestParam @NotNull String content) {

        // 创建一个响应结果的映射
        Map<String, Object> response = new HashMap<>();
        try {
            // 调用服务方法发送电子邮件
            springEmailService.sendEmail(to, subject, content);

            // 成功消息
            response.put("message", "电子邮件发送成功！");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 错误处理
            response.put("error", "发送电子邮件失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
