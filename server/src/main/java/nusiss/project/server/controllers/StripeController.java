package nusiss.project.server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import nusiss.project.server.models.MaintenanceCheckout;

@RestController
@RequestMapping(path = "/payment")
public class StripeController {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/maintenance")
  public ResponseEntity<String> maintenanceCheckoutPage(@RequestBody MaintenanceCheckout checkout)
      throws StripeException {

    // We initilize stripe object with the api key
		Stripe.apiKey = "sk_test_51NKBUaA6ZeborKVNjxDQrN7pNeJEyhO38ruJhDjzrOBcrtJoIU3G2YRShHWjChVcLLM2JjKz7oFgiZdGt6zJcK51008A9f7hCa";
    
    SessionCreateParams params = new SessionCreateParams.Builder().setSuccessUrl(checkout.getSuccessUrl())
        .setCancelUrl(checkout.getCancelUrl()).addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
        .setMode(SessionCreateParams.Mode.SUBSCRIPTION).addLineItem(new SessionCreateParams.LineItem.Builder()
            .setQuantity(1L).setPrice(checkout.getPriceId()).build())
        .build();

    try {
      Session session = Session.create(params);
      Map<String, Object> responseData = new HashMap<>();
      responseData.put("sessionId", session.getId());
      return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));

    } catch (Exception e) {

      Map<String, Object> messageData = new HashMap<>();
      messageData.put("id", e.getMessage());
      Map<String, Object> responseData = new HashMap<>();
      responseData.put("error", messageData);
      try {
        return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(responseData));
      } catch (JsonProcessingException jsonException) {
        return ResponseEntity.badRequest().body("{\"message\":\"An error occurred\"}");
      }
      
    }
  }
}
