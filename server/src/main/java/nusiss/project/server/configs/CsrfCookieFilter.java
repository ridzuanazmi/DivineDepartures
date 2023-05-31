package nusiss.project.server.configs;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Reads and stores the CSRF token from current request attribute 
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Sets an HTTP response header with the name of the CSRF token header and the value of the CSRF token
        // if not null 
        if (csrfToken != null) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        // After the filter has done its authentication or authorization checks, 
        // allowing the request to proceed to the next filter or the target resource if the checks pass
        filterChain.doFilter(request, response);
    }

}
