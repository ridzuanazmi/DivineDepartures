package nusiss.project.server.services;

import java.sql.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nusiss.project.server.models.AuthenticationRequest;
import nusiss.project.server.models.AuthenticationResponse;
import nusiss.project.server.models.RegisterRequest;
import nusiss.project.server.models.user.Role;
import nusiss.project.server.models.user.User;
import nusiss.project.server.repositories.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtSrvc;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepo,
            PasswordEncoder passwordEncoder,
            JwtService jwtSrvc,
            AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtSrvc = jwtSrvc;
        this.authenticationManager = authenticationManager;
    }

    // register method allow us to create a user, save it to the DB and return
    // generated token in AuthenticationRequest (AR) object 
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode the password to be stored safely in DB
                .createdDate(new Date(System.currentTimeMillis()))
                .role(Role.USER) // Sets the role to user all the time 
                .build();
        userRepo.save(user); // Save the User object into MySQL

        var jwtToken = jwtSrvc.generateToken(user); // to generate token from the request
        return AuthenticationResponse.builder() // build the AR object (immutable)
                .token(jwtToken)
                .build();
    }

    // method authenticates a user's credentials and, if successful, generates a JWT
    // for that user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())); // will throw an exception if fail

        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        var jwtToken = jwtSrvc.generateToken(user); // to generate token from the request

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
