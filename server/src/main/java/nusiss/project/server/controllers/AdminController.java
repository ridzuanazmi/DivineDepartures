package nusiss.project.server.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.project.server.models.UserDto;
import nusiss.project.server.models.user.User;
import nusiss.project.server.repositories.MySqlRepository;

@RestController
@RequestMapping("/dashboard")
public class AdminController {

  private final MySqlRepository sqlRepo;
  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

  public AdminController(MySqlRepository sqlRepo) {
    this.sqlRepo = sqlRepo;
  }

  @GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> getAllAccounts() {
    LOGGER.info("\nGET /dashboard/accounts - start");

    try {
      List<User> accounts = sqlRepo.getAllAccounts();
      return new ResponseEntity<>(accounts, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.error("Exception: ", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/accounts/{id}")
  public ResponseEntity<User> getAccountById(@PathVariable String id) {
    LOGGER.info("\nGET /dashboard/accounts/{} - start", id);

    try {
      Optional<User> account = sqlRepo.getAccountById(id);
      if (account.isPresent()) {
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      LOGGER.error("Exception: ", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/accounts/{id}")
  public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
    LOGGER.info("\nDELETE /dashboard/accounts/" + id + " - start");

    try {
      sqlRepo.deleteAccountById(id);
      return ResponseEntity.ok("Account with ID " + id + " was successfully deleted");
    } catch (Exception e) {
      LOGGER.error("Error deleting account: " + id, e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with ID " + id + " does not exist");
    }
  }

  @PutMapping("/accounts/{id}")
  public ResponseEntity<String> updateAccount(@PathVariable Integer id, @RequestBody UserDto user) {
    LOGGER.info("PUT /dashboard/account/{} - start", id);
    LOGGER.info("PUT /dashboard/account user: {} - start", user);

    try {
      sqlRepo.updateAccount(id, user);
      return ResponseEntity.ok("Account updated successfully");
    } catch (Exception e) {
      LOGGER.error("Account update failed: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account update failed");
    }
  }
}
