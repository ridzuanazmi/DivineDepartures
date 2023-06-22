package nusiss.project.server.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nusiss.project.server.models.AuthenticationRequest;
import nusiss.project.server.models.AuthenticationResponse;
import nusiss.project.server.models.RegisterRequest;
import nusiss.project.server.models.user.User;
import nusiss.project.server.repositories.UserRepository;

@Service
public class AuthenticationService {

        private final UserRepository userRepo;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtSrvc;
        private final AuthenticationManager authenticationManager; // Can authenticate base on username and password

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
        // a response to indicate that registration is successful
        public String register(RegisterRequest request) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> response = new HashMap<>();

                Optional<User> existingUser = userRepo.findByEmail(request.getEmail());

                if (existingUser.isPresent()) {
                        response.put("message", "Email " + request.getEmail() + " is already registered.");
                        try {
                                return mapper.writeValueAsString(response);
                        } catch (JsonProcessingException e) {
                                e.printStackTrace();
                                return "{\"message\":\"Error occurred while processing the request.\"}";
                        }
                }
                var user = User.builder()
                                .fullName(request.getFullName())
                                .phoneNumber(request.getPhoneNumber())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword())) // Encrypt the password to be
                                                                                         // stored safely in DB
                                .createdDate(new Date(System.currentTimeMillis()))
                                .role(request.getRole()) // Sets the role to user all the time
                                .build();
                userRepo.save(user); // Save the User object into MySQL

                try {
                        userRepo.save(user); // Save the User object into MySQL
                        response.put("message", "User registration successful!");
                        return mapper.writeValueAsString(response);
                } catch (Exception e) {
                        response.put("message", "User registration failed!");
                        response.put("error", e.getMessage());
                        try {
                                return mapper.writeValueAsString(response);
                        } catch (JsonProcessingException ex) {
                                ex.printStackTrace();
                                return "{\"message\":\"Error occurred while processing the request.\"}";
                        }
                }

                // var jwtToken = jwtSrvc.generateToken(user); // to generate token from the
                // request
                // return AuthenticationResponse.builder() // build the AR object (immutable)
                // .token(jwtToken)
                // .build();
        }

        // method authenticates a user's credentials and, if successful, generates a JWT
        // for that user
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword())); // will throw an exception if incorrect
                                                                         // username or password

                var user = userRepo.findByEmail(request.getEmail())
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "User not found with email: " + request.getEmail()));
                System.out.println(user.toString()); // This contains all the user info in the _user table in MySQL

                Map<String, Object> extraClaims = new HashMap<>();
                extraClaims.put("id", user.getUserId());
                extraClaims.put("fullName", user.getFullName());
                extraClaims.put("phoneNumber", user.getPhoneNumber());

                var jwtToken = jwtSrvc.generateToken(extraClaims ,user); // to generate token from the request

                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

}
