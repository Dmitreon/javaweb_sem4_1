package com.example.javaweb_sem4_1.util.utility;

import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.security.EmailConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static volatile EmailSender instance;
    private Properties mailProps;

    private EmailSender() {
        mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.mailtrap.io");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.port", "2525");
        mailProps.put("mail.smtp.starttls.enable", "true");
    }

    public static EmailSender getInstance() {
        if (instance == null) {
            synchronized (EmailSender.class) {
                if (instance == null) {
                    instance = new EmailSender();
                }
            }
        }
        return instance;
    }

    public void sendEmail(String receiver, int verificationCode) throws ServiceException {
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConfig.USERNAME, EmailConfig.PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailConfig.USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Verification code");
            message.setText("Your verification code: " + verificationCode);

            Transport.send(message);
        } catch (Exception e) {
            throw new ServiceException("Failed to send email", e);
        }
    }
}
