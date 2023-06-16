package nusiss.project.server.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender javaMailSender;

  public EmailService(
      JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendWelcomeEmail(
    String userEmail, 
    String subject, 
    String welcomeMessage) {

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(userEmail);
      message.setSubject(subject);
      message.setText(welcomeMessage);
      message.setFrom("DivineDepartures");
      javaMailSender.send(message);

      System.out.println("Welcome email sent successfully");
  }

  public void sendContactEmail(
    String contactEmail, 
    String subject, 
    String contactMessage) {

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo("divinedepartures.11@gmail.com");
      message.setSubject(subject);
      message.setText(contactMessage);
      message.setFrom(contactEmail);
      javaMailSender.send(message);

      System.out.println("Welcome email sent successfully");
  }
}
