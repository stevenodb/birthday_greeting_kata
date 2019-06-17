package com.cegeka.birthday.infrastructure;

import com.cegeka.birthday.domain.BirthdayMessage;
import com.cegeka.birthday.domain.Employee;
import com.cegeka.birthday.domain.MessagingService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl implements MessagingService {

    private final String senderAddress;
    private String smtpHost;
    private int smtpPort;

    public EmailServiceImpl(String smtpHost, int smtpPort, String senderAddress) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.senderAddress = senderAddress;
    }

    @Override
    public void sendMessage(Employee recipient, BirthdayMessage message) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(senderAddress));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail()));
            msg.setSubject(message.getHeading());
            msg.setText(message.getBody());
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
