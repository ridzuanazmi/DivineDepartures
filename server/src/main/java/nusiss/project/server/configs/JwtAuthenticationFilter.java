package nusiss.project.server.configs;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nusiss.project.server.services.JwtService;

// This filter will be active every time server gets a HTTP request 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtSrvc;
    private final UserDetailsService userDetailsSrvc;

    public JwtAuthenticationFilter(JwtService jwtSrvc, UserDetailsService userDetailsSrvc) {
        this.jwtSrvc = jwtSrvc;
        this.userDetailsSrvc = userDetailsSrvc;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Null pointer exceptions
        if (null == request)
            throw new NullPointerException("request is marked non-null but is null\n");
        if (null == response)
            throw new NullPointerException("response is marked non-null but is null\n");
        if (null == filterChain)
            throw new NullPointerException("filterChain is marked non-null but is null\n");

        // Stores the authentication header
        final String authHeader = request.getHeader("Authorization");
        // Stores the extracted JWT from the authentication header
        final String jwt; // JWT token
        // Stores the extracted email from the user
        final String userEmail;

        // Check on the JWT
        if (null == authHeader || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // pass the request and response to the next filter
            return; // terminate, don't want to continue with the rest of the execution
        }

        // Extract the token from the authHeader
        jwt = authHeader.substring(7); 
        // Extract the user's email from JWT token using a service class
        userEmail = jwtSrvc.extractUsername(jwt);  

        // Checks if the user email is not null and already authenticated ot not
        if (null != userEmail && null == SecurityContextHolder.getContext().getAuthentication()) {
            // means userEmail exists and authenticated
            UserDetails userDetails = this.userDetailsSrvc.loadUserByUsername(userEmail);
            if (jwtSrvc.isTokenValid(jwt, userDetails)) {
                // if token is valid, update the SecurityContextHolder and send request to DispatcherServlet
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                ); // authToken is needed by Spring and SecurityContextHolder in order to update SecurityContext
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); // updates the SecurityContextHolder
            }
        }
        
        // Stores the CSRF token from current request attribute 
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Sets an HTTP response header with the name of the CSRF token header and the value of the CSRF token
        // if not null 
        if (csrfToken != null) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        // After the filter has done its authentication or authorization checks, 
        // allowing the request to proceed to the next filter or the target resource if the checks pass
        filterChain.doFilter(request, response); 
    } // End of doFilterInternal()
}