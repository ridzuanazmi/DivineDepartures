package nusiss.project.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.project.server.models.AuthenticationRequest;
import nusiss.project.server.models.AuthenticationResponse;
import nusiss.project.server.models.RegisterRequest;
import nusiss.project.server.services.AuthenticationService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final AuthenticationService authSrvc;

    public AuthenticationController(AuthenticationService authSrvc) {
        this.authSrvc = authSrvc;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authSrvc.register(request));
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authSrvc.authenticate(request));
    }

}
