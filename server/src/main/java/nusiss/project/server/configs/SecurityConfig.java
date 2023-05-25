package nusiss.project.server.configs;

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

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf"); // Default is "_csrf" only for clarity

        /*
         * we need to let Spring Security framework, that, "Please create the JSESSIONID by following this sessionManagement
         * that I have created here." So with this configurations we are telling to the Spring Security framework,
         * "Please always create the JSESSIONID after the initial login is completed." And the same JSESSIONID it is going to send
         * to the UI application and my UI application can leverage the same
         * for all the subsequent requests that it is going to make after the initial login.
         * securityContext().requireExplicitSave(false)
         * .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
         */

        httpSec.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Configure session management, should not store authentication/session state (aka stateless)
                .and()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers("/contact/**", "/auth/**")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests()
                    .requestMatchers("/auth/**", "/contact/**").permitAll() // whitelists the specified request matchers
                    .anyRequest().authenticated() // any other requests needs to be authenticated
                .and() // .and() to add new config
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSec.build();
    }
}
