package it.aces.vlad_project.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Transactional
    public void sendEmail(int operation, String to, String body) {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            List<String> messages = generateMessage(operation, body, currentDateTime);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(to);
            messageHelper.setSubject(messages.getFirst());
            messageHelper.setText(messages.get(1));
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("При отправке сообщения произошла ошибка!");
        }
    }

    private List<String> generateMessage(int operation, String obj, LocalDateTime currentDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        String title = "%s %s от %s.";
        String message = "С вашего аккаунта %s была %s сущность %s.";
        List<String> messages = new ArrayList<>();
        switch (operation) {
            case 1:
                messages.add(String.format(title, "Создание", obj, formattedDateTime));
                messages.add(String.format(message, formattedDateTime, "создана", obj));
                break;
            case 2:
                messages.add(String.format(title, "Редактирование", obj, formattedDateTime));
                messages.add(String.format(message, formattedDateTime, "отредактирована", obj));
                break;
            case 3:
                messages.add(String.format(title, "Удаление", obj, formattedDateTime));
                messages.add(String.format(message, formattedDateTime, "удалена", obj));
                break;
        }
        return messages;
    }
}
