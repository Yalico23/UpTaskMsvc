package com.msvc_test.infrastructure.adapaters;

import com.msvc_test.domain.port.output.EmailExternalPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class EmailExternalAdapter implements EmailExternalPort {

    private final JavaMailSender mailSender;

    public EmailExternalAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public CompletableFuture<Void> sendEmail(String to, String from, String subject, String body, String htmlText) {
        return CompletableFuture.runAsync(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setSubject(subject);
                helper.setFrom(from);
                helper.setTo(to);
                helper.setText(body, htmlText);
                mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Error al enviar el correo electrónico", e);
            }
        });
    }
}
