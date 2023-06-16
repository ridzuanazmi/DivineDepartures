package nusiss.project.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.project.server.models.AuthenticationRequest;
import nusiss.project.server.models.AuthenticationResponse;
import nusiss.project.server.models.RegisterRequest;
import nusiss.project.server.services.AuthenticationService;
import nusiss.project.server.services.EmailService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final AuthenticationService authSrvc;
    private final EmailService emailSrvc;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(
            AuthenticationService authSrvc,
            EmailService emailSrvc) {
        this.authSrvc = authSrvc;
        this.emailSrvc = emailSrvc;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        try {
            String response = authSrvc.register(request);
            if (response.contains("successful")) {

                // Send email on successful registration
                String userEmail = request.getEmail();
                String subject = "Welcome to Divine Departures";
                String message = "Dear " + request.getLastName() + ", " + request.getFirstName() + ",\n\n" +
                        "Thank you for registering with Divine Departures. We are delighted to have you join our community.\n\n"
                        +
                        "At Divine Departures, we are committed to helping you honour your loved ones with dignity and respect. We offer a range of services including grave maintenance, memorial services, grave decoration, and custom tombstones. Each of these services is delivered with the utmost professionalism and care, ensuring that your loved ones' final resting place is a fitting tribute to their life.\n\n"
                        +
                        "You can learn more about our services on our website. And if you have any questions or require further assistance, please do not hesitate to get in touch with us. Our team is here to assist you every step of the way.\n\n"
                        +
                        "Once again, thank you for choosing Divine Departures. We look forward to serving you.\n\n" +
                        "Best Regards,\n\n" +
                        "The Divine Departures Team\n\n" +
                        "This is an automated message, please do not reply to this email.";
                // Send email and log if successful or not
                try {
                    emailSrvc.sendWelcomeEmail(userEmail, subject, message);
                } catch (MailException e) {
                    LOGGER.error("Failed to send welcome email", e);
                }
                // Return successfully created 
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error occurred while processing the request.\"}");
        }
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authSrvc.authenticate(request)); // Returns an auth JWT
    }

}
