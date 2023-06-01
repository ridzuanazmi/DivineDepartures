package nusiss.project.server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import nusiss.project.server.repositories.UserRepository;

@Configuration
public class AppConfig {

    private final UserRepository userRepo;
    
    public AppConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Fetch the user from MySQL into UserDetails
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // AuthenticationProvider is the DAO which is responsible to fetch UserDetails,
        // encode password and etc.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Tell authProvider which userDetailsService to use in order to fetch info on users
        authProvider.setUserDetailsService(userDetailsService()); // from the above method
        // Which password encoder using in order to decode the password using the correct algorithm 
        authProvider.setPasswordEncoder(passwordEncoder()); 
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
