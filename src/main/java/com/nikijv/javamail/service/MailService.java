package com.nikijv.javamail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendText(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);
            helper.setFrom("nekit3310@gmail.com");

            mailSender.send(message);
            System.out.println("text mail sent successfully");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendHtmlWithTemplate(String to, String subject, String templateName, Map<String, Object> variables) {
        try {

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(templateName, context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom("nekit3310@gmail.com");

            mailSender.send(message);
            System.out.println("HTML sent successfully!");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);
            helper.setFrom("nekit3310@gmail.com");

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(message);
            System.out.println("Attachment sent successfully!");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
