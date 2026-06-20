package com.gerenciamento.imoveis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCodigoRecuperacao(String destinatario, String codigo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(destinatario);
        message.setSubject("Código de recuperação de senha - Gestão de Bens");
        message.setText(
            "Seu código de recuperação de senha é: " + codigo + "\n\n" +
            "Esse código expira em 15 minutos. Se você não solicitou essa recuperação, ignore este e-mail."
        );
        mailSender.send(message);
    }
}