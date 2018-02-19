package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.MailType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import static org.mockito.ArgumentMatchers.*;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage());
        };

        //When
        simpleEmailService.send(mail, MailType.NEW_TRELLO_CARD_INFO);

        //Then
        Mockito.verify(javaMailSender, Mockito.times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendEmailWithCc() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message",
                "test1@test.com", "test2@test.com");

        //When
        simpleEmailService.send(mail, MailType.NEW_TRELLO_CARD_INFO);

        //Then
        Mockito.verify(javaMailSender, Mockito.times(1)).send(any(MimeMessagePreparator.class));
    }
}