package nusiss.project.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        LOGGER.info("POST /auth/register - Start - RegisterRequest: {}", request);
        try {
            String response = authSrvc.register(request);
            if (response.contains("successful")) {
                // Send email on successful registration
                emailSrvc.sendWelcomeEmail(request);
                LOGGER.info("POST /auth/register - Successful registration - Response: {}", response);
                // Return successfully created
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                LOGGER.info("POST /auth/register - Registration not successful - Response: {}", response);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error occurred while processing the request.\"}");
        } finally {
            LOGGER.info("POST /auth/register - End");
        }
    }

    // TODO: improve security by limiting the number of login attempts
    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authSrvc.authenticate(request)); // Returns an auth JWT
    }

}
