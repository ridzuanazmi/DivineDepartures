package nusiss.project.server.controllers;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.project.server.models.Shop;
import nusiss.project.server.models.ShopDto;
import nusiss.project.server.services.EmailService;
import nusiss.project.server.services.ShopService;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

  private final EmailService emailSrvc;
  private final ShopService shopSrvc;
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

  public EmailController(
      EmailService emailSrvc,
      ShopService shopSrvc) {
    this.emailSrvc = emailSrvc;
    this.shopSrvc = shopSrvc;
  }

  @PostMapping("/shop")
  public ResponseEntity<Map<String, String>> sendShopEmail(@RequestBody ShopDto shopDto) {

    LOGGER.info("POST /email/shop");
    LOGGER.debug(shopDto.toString());
    // Create a shop object from the request (with the user details) and saves it in the DB
    Shop shop = shopSrvc.createShop(shopDto);
    System.out.println(shop.toString());

    String contactEmail = shopDto.getEmail();
    String subject = "New Shop Order from " + shopDto.getEmail();
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
        "Please review the order in the admin panel.";

    // Send email and response if successful or not
    try {
      emailSrvc.sendShopEmail(contactEmail, subject, message);
    } catch (MailException e) {
      LOGGER.error("Failed to send welcome email", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Failed to send email"));
    }

    return ResponseEntity.ok(Collections.singletonMap("message", "Email sent successfully"));
  }

  // Method done together with register controller
  // @PostMapping("/welcome")
  // public ResponseEntity<Map<String, String>> sendWelcomeEmail(@RequestBody User user) {

  //   LOGGER.info("POST /email/welcome");
  //   LOGGER.debug(user.toString());

  //   String userEmail = user.getEmail();
  //   String subject = "Welcome to Divine Departures";
  //   String message = "Dear " + user.getLastName() + ", " + user.getFirstName() + ",\n\n" +
  //       "Thank you for registering with Divine Departures. We are delighted to have you join our community.\n\n" +
  //       "At Divine Departures, we are committed to helping you honour your loved ones with dignity and respect. We offer a range of services including grave maintenance, memorial services, grave decoration, and custom tombstones. Each of these services is delivered with the utmost professionalism and care, ensuring that your loved ones' final resting place is a fitting tribute to their life.\n\n"
  //       +
  //       "You can learn more about our services on our website. And if you have any questions or require further assistance, please do not hesitate to get in touch with us. Our team is here to assist you every step of the way.\n\n"
  //       +
  //       "Once again, thank you for choosing Divine Departures. We look forward to serving you.\n\n" +
  //       "Best Regards,\n\n" +
  //       "The Divine Departures Team\n\n" +
  //       "This is an automated message, please do not reply to this email.";

  //   // Send email and response if successful or not
  //   try {
  //     emailSrvc.sendWelcomeEmail(userEmail, subject, message);
  //   } catch (MailException e) {
  //     LOGGER.error("Failed to send welcome email", e);
  //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
  //         .body(Collections.singletonMap("message", "Failed to send email"));
  //   }

  //   return ResponseEntity.ok(Collections.singletonMap("message", "Email sent successfully"));
  // }

  // Do it together with contact-us page lol!
  // @PostMapping("/contact-us")
  // public ResponseEntity<Map<String, String>> sendContactEmail(@RequestBody Contact contact) {

  //   System.out.println("POST /email/welcome");
  //   System.out.println(contact.toString());

  //   String contactEmail = contact.getContactEmail();
  //   String subject = "New contact message from " + contact.getContactName();
  //   String message = "Hello Team,\n\n" +
  //       "We've received a new message through the Contact Us form on our website.\n\n" +
  //       "Here are the details:\n\n" +
  //       "Name: " + contact.getContactName() + "\n" +
  //       "Email: " + contact.getContactEmail() + "\n" +
  //       "Subject: " + contact.getSubject() + "\n" +
  //       "Message: " + contact.getMessage() + "\n" +
  //       "Submitted On: " + contact.getCreateDate() + "\n\n" +
  //       "Please review this message and take the necessary action ASAP.\n\n" +
  //       "Best Regards,\n\n" +
  //       "Automated Contact Form System\n\n" +
  //       "This is an automated message, please do not reply to this email.";

  //   // Send email and response if successful or not
  //   try {
  //     emailSrvc.sendContactEmail(contactEmail, subject, message);
  //   } catch (MailException e) {
  //     LOGGER.error("Failed to send welcome email", e);
  //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
  //         .body(Collections.singletonMap("message", "Failed to send email"));
  //   }

  //   return ResponseEntity.ok(Collections.singletonMap("message", "Email sent successfully"));
  // }

}
