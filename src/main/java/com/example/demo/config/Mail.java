//package com.example.demo.config;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.Data;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import java.util.Properties;
//
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "mail")
//public class Mail {
//    public String host;
//    public Integer port;
//    public String username;
//    public String password;
//    public Boolean smtpAuth;
//    public Boolean smtpStarttlsEnable;
//    public Boolean isSendToCC;
//
//    private static final Logger logger = LoggerFactory.getLogger(Mail.class);
//
//
//    @Bean
//    public JavaMailSender configMail() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", smtpAuth);
//        props.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
//        props.put("mail.debug", "true");
//        return mailSender;
//
//    }
//
//    public void sendSimpleMessageWithToAndCC(String subject, String body, String to, String cc) throws MessagingException, jakarta.mail.MessagingException {
//        MimeMessage message = configMail().createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
//
//        if (to != null && !to.isEmpty()) {
//            String[] toAddress = to.split(",");
//            helper.setTo(toAddress);
//        }
//
//        if (cc != null && !cc.isEmpty() && isSendToCC) {
//            String[] ccAddress = cc.split(",");
//            helper.setCc(ccAddress);
//        }
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        configMail().send(message);
//
//    }
//
//
//}
