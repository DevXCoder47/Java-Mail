package com.nikijv.javamail.controller;

import com.nikijv.javamail.entity.Mail;
import com.nikijv.javamail.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MailController {
    MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send-text")
    public ResponseEntity<?> sendText (@RequestBody Mail mail) {
        mailService.sendText(mail.to, mail.subject, mail.body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-html")
    public ResponseEntity<?> sendHtml (@RequestBody Mail mail) {
        Map<String, Object> variables = Map.of("name", "Nikita");
        mailService.sendHtmlWithTemplate(mail.to, mail.subject, mail.body, variables);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-attachment")
    public ResponseEntity<?> sendTextWithAttachment (@RequestBody Mail mail) {
        mailService.sendAttachment(mail.to, mail.subject, mail.body, "C:\\Users\\nik\\Desktop\\windows-10-abstract-logo-4k-colorful-backgrounds-os-windows-10-logo.jpg");
        return ResponseEntity.ok().build();
    }
}
//http://localhost:8080/swagger-ui/index.html
//nekit3310@gmail.com
