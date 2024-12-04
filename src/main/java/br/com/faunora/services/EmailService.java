package br.com.faunora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("redefinição de senha");
        message.setText("o link para a redefinição de senha do seu perfil no faunora é: " + token);
        message.setFrom("faunorasystem@gmail.com");
        mailSender.send(message);
    }
}
