package nusiss.project.server.controllers;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

    LOGGER.info("POST /email/shop - Start - ShopDto: {}", shopDto);
    LOGGER.debug(shopDto.toString());
    // Create a shop object from the request (with the user details) and saves it in
    // the DB
    Shop shop = shopSrvc.createShop(shopDto);
    LOGGER.info("POST /email/shop - Shop created - Shop: {}", shop);

    try {
      // Create and send email to DD team
      emailSrvc.sendShopEmailToTeam(shopDto, shop);
      emailSrvc.sendShopEmailToUser(shopDto, shop);
      LOGGER.info("POST /email/shop - Email sent successfully");
      return ResponseEntity.ok(Collections.singletonMap("message", "Email sent successfully"));
    } catch (Exception e) {
      return ResponseEntity.ok(Collections.singletonMap("message", "Email sent UNsuccessful"));
    } finally {
      LOGGER.info("POST /email/shop - End");
    }

  }
}
