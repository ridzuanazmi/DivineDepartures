package nusiss.project.server.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {

        // Makes CSRF token available as a request attribute and resolving the token value as a header or parameter value
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf"); // Default is "_csrf" this is only for clarity

        /*
         * we need to let Spring Security framework, that, "Please create the JSESSIONID by following this sessionManagement
         * that I have created here." So with this configurations we are telling to the Spring Security framework,
         * "Please always create the JSESSIONID after the initial login is completed." And the same JSESSIONID it is going to send
         * to the UI application and my UI application can leverage the same
         * for all the subsequent requests that it is going to make after the initial login.
         * securityContext().requireExplicitSave(false)
         * .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
         */

        httpSec.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Configure session management, should not store authentication/session state (aka stateless) and each request should be authenticated
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
                        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Arrays.asList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        return config;
                    }
                })
                        .and()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers("/contact-us", "/auth/**") // ignores request so CSRF is disabled only here
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Persists the CSRF token in a cookie with the default name and can read the cookie value in Angular
                    .addFilterAfter(new CsrfCookieFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                    .requestMatchers("/auth/**", "/contact-us").permitAll() // whitelists the specified request matchers
                    .anyRequest().authenticated() // any other requests needs to be authenticated
                .and() // .and() to add new config
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSec.build();
    }
}
