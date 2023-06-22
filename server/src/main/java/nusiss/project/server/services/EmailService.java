package nusiss.project.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import nusiss.project.server.models.Contact;
import nusiss.project.server.models.MaintenanceCheckout;
import nusiss.project.server.models.RegisterRequest;
import nusiss.project.server.models.Shop;
import nusiss.project.server.models.ShopDto;

@Service
public class EmailService {

  private final JavaMailSender javaMailSender;
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  public EmailService(
      JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  // Create and send welcome email to Customer
  public void sendWelcomeEmail(RegisterRequest request) {
    String userEmail = request.getEmail();
    String subject = "Welcome to Divine Departures";
    String message = "Dear " + request.getFullName() + ",\n\n" +
        "Thank you for registering with Divine Departures. We are delighted to have you join our community.\n\n"
        +
        "At Divine Departures, we are committed to helping you honour your loved ones with dignity and respect. We offer a range of services including grave maintenance, memorial services, grave decoration, and custom tombstones. Each of these services is delivered with the utmost professionalism and care, ensuring that your loved ones' final resting place is a fitting tribute to their life.\n\n"
        +
        "You can learn more about our services on our website. And if you have any questions or require further assistance, please do not hesitate to get in touch with us. Our team is here to assist you every step of the way.\n\n"
        +
        "Feel free to call us at +65 6666 7777 and visit us at 550 Choa Chu Kang St 52" + "\n\n" +
        "Once again, thank you for choosing Divine Departures. We look forward to serving you.\n\n" +
        "Best Regards,\n\n" +
        "The Divine Departures Team\n\n" +
        "This is an automated message, please do not reply to this email.";
    // Send email and log if successful or not
    try {
      sendWelcomeEmail(userEmail, subject, message);
      LOGGER.info("Email sent to customer {}", userEmail);
    } catch (MailException e) {
      LOGGER.error("Failed to send welcome email", e);
    }
  }

  // Create and send contact us email to DD team
  public void sendContactEmail(Contact contact) {
    String contactEmail = contact.getContactEmail();
    String subject = "New contact message from " + contact.getContactName();
    String message = "Hello Team,\n\n" +
        "We've received a new message through the Contact Us form on our website.\n\n" +
        "Here are the details:\n\n" +
        "Name: " + contact.getContactName() + "\n" +
        "Email: " + contact.getContactEmail() + "\n" +
        "Subject: " + contact.getSubject() + "\n" +
        "Message: " + contact.getMessage() + "\n" +
        "Submitted On: " + contact.getCreateDate() + "\n\n" +
        "Please review this message and take the necessary action ASAP.\n\n" +
        "Best Regards,\n\n" +
        "Automated Contact Form System\n\n" +
        "This is an automated message, please do not reply to this email.";

    // Send email and response if successful or not
    try {
      sendContactEmail(contactEmail, subject, message);
      LOGGER.info("Email sent to team regarding contact-us from {}", contactEmail);
    } catch (MailException e) {
      LOGGER.error("Failed to send contact-us email", e);
    }
  }

  // Create and send shop email to DD team
  public void sendShopEmailToTeam(ShopDto shopDto, Shop shop) {
    String contactEmail = shopDto.getEmail();
    String subject = "New Shop Order from " + shopDto.getFullName();
    String message = "A new shop order has been placed by " + shopDto.getEmail() + ". The details are as follows:\n\n" +
        "Deceased Name: " + shop.getDeceasedName() + "\n" +
        "Block: " + shop.getBlock() + "\n" +
        "Plot Number: " + shop.getPlotNumber() + "\n" +
        "Date of Death: " + shop.getDateOfDeath() + "\n" +
        "Tombstone Height: " + shop.getTombstoneHeight() + "\n" +
        "Tombstone Material: " + shop.getTombstoneMaterial() + "\n" +
        "Tiles: " + shop.getTiles() + "\n" +
        "Curved Mosaic Tile: " + shop.getCurvedMosaicTile() + "\n" +
        "Top Cover: " + shop.getTopCover() + "\n" +
        "Plant: " + shop.getPlant() + "\n\n" +
        "Phone Number: " + shopDto.getPhoneNumber() + "\n\n" +
        "Please review the order in the admin panel.";
    // Send email and response if successful or not
    try {
      sendShopEmailToTeam(contactEmail, subject, message);
      LOGGER.info("Email sent to team regarding order form from {}", contactEmail);
    } catch (MailException e) {
      LOGGER.error("Failed to send shop email", e);
    }
  }

  // Create and send shop email to User
  public void sendShopEmailToUser(ShopDto shopDto, Shop shop) {
    String contactEmail = shopDto.getEmail();
    String subject = "Your Shop Order Confirmation";
    String message = "Dear " + shopDto.getFullName() + ",\n\n" +
        "Your shop order has been successfully placed. The details are as follows:\n\n" +
        "Deceased Name: " + shop.getDeceasedName() + "\n" +
        "Block: " + shop.getBlock() + "\n" +
        "Plot Number: " + shop.getPlotNumber() + "\n" +
        "Date of Death: " + shop.getDateOfDeath() + "\n" +
        "Tombstone Height: " + shop.getTombstoneHeight() + "\n" +
        "Tombstone Material: " + shop.getTombstoneMaterial() + "\n" +
        "Tiles: " + shop.getTiles() + "\n" +
        "Curved Mosaic Tile: " + shop.getCurvedMosaicTile() + "\n" +
        "Top Cover: " + shop.getTopCover() + "\n" +
        "Plant: " + shop.getPlant() + "\n\n" +
        "Our team will get back to you shortly regarding your order. Thank you for your patience.\n\n" +
        "Best regards,\n" +
        "Divine Departures team";
    // Send email and response if successful or not
    try {
      sendShopEmailToUser(contactEmail, subject, message);
      LOGGER.info("Order confirmation email sent to {}", contactEmail);
    } catch (MailException e) {
      LOGGER.error("Failed to send order confirmation email", e);
    }
  }

  // Create and send maintenance email to DD team
  public void sendMaintenanceEmailToTeam(MaintenanceCheckout checkout) {
    String priceId = checkout.getPriceId();
    String maintenanceType;

    if (priceId.equals("price_1NKCkIA6ZeborKVNqIWcveSE")) {
      maintenanceType = "Quarterly";
    } else {
      maintenanceType = "Yearly";
    }
    String contactEmail = checkout.getEmail();
    String subject = "New Maintenance Service Subscription";
    String message = "A new Maintenance Service Subscription has been made.\n" +
        "Customer Name: " + checkout.getFullName() + "\n" +
        "Customer Email: " + checkout.getEmail() + "\n" +
        "Plot Number: " + checkout.getPlotNumber() + "\n" +
        "Block Number: " + checkout.getBlockNumber() + "\n" +
        "Maintenance type: " + maintenanceType;

    // Send email and response if successful or not
    try {
      sendMaintenanceEmailToTeam(contactEmail, subject, message);
      LOGGER.info("Email sent to team regarding new subscription from {}", checkout.getEmail());
    } catch (MailException e) {
      LOGGER.error("Failed to send welcome email", e);
    }
  }

  // Create and send maintenance email to user
  public void sendMaintenanceEmailToUser(MaintenanceCheckout checkout) {
    String priceId = checkout.getPriceId();
    String maintenanceType;

    if (priceId.equals("price_1NKCkIA6ZeborKVNqIWcveSE")) {
      maintenanceType = "Quarterly";
    } else {
      maintenanceType = "Yearly";
    }
    String customerEmail = checkout.getEmail();
    String subject = "Maintenance Service Subscription Successful";
    String message = "Dear " + checkout.getFullName() + ",\n\n" +
        "Your Maintenance Service Subscription has been successfully processed.\n" +
        "Plot Number: " + checkout.getPlotNumber() + "\n" +
        "Block Number: " + checkout.getBlockNumber() + "\n" +
        "Maintenance type: " + maintenanceType + "\n" +
        "We will send before and after pictures through WhatsApp" + "\n\n" +
        "Feel free to call us at +65 6666 7777 and visit us at 550 Choa Chu Kang St 52" + "\n\n" +
        "Thank you for choosing our services and for trusting us.\n" +
        "Best Regards, \n Divine Departures Team";

    // Send email and response if successful or not
    try {
      sendMaintenanceEmailToUser(customerEmail, subject, message);
      LOGGER.info("Confirmation email sent to {}", checkout.getEmail());
    } catch (MailException e) {
      LOGGER.error("Failed to send confirmation email", e);
    }
  }

  // Helper methods
  private void sendWelcomeEmail(
      String userEmail,
      String subject,
      String welcomeMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(userEmail);
    message.setSubject(subject);
    message.setText(welcomeMessage);
    message.setFrom("divinedepartures.11@gmail.com");
    javaMailSender.send(message);

    System.out.println("Welcome email sent successfully");
  }

  private void sendContactEmail(
      String contactEmail,
      String subject,
      String contactMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("divinedepartures.11@gmail.com");
    message.setSubject(subject);
    message.setText(contactMessage);
    message.setFrom(contactEmail);
    javaMailSender.send(message);

    System.out.println("Contact email sent successfully");
  }

  private void sendShopEmailToUser(
      String contactEmail,
      String subject,
      String contactMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(contactEmail);
    message.setSubject(subject);
    message.setText(contactMessage);
    message.setFrom("divinedepartures.11@gmail.com");
    javaMailSender.send(message);

    System.out.println("Order email sent successfully to user");
  }

  private void sendShopEmailToTeam(
      String contactEmail,
      String subject,
      String contactMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("divinedepartures.11@gmail.com");
    message.setSubject(subject);
    message.setText(contactMessage);
    message.setFrom(contactEmail);
    javaMailSender.send(message);

    System.out.println("Order email sent successfully to team");
  }

  private void sendMaintenanceEmailToTeam(
      String contactEmail,
      String subject,
      String contactMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("divinedepartures.11@gmail.com");
    message.setSubject(subject);
    message.setText(contactMessage);
    message.setFrom(contactEmail);
    javaMailSender.send(message);

    System.out.println("Maintenance email sent successfully to team");
  }

  private void sendMaintenanceEmailToUser(
      String contactEmail,
      String subject,
      String contactMessage) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(contactEmail);
    message.setSubject(subject);
    message.setText(contactMessage);
    message.setFrom("divinedepartures.11@gmail.com");
    javaMailSender.send(message);

    System.out.println("Maintenance email sent successfully to customer");
  }
}
