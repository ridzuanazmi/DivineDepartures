package nusiss.project.server.configs;

import java.util.UUID;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

public class SingleUseCsrfTokenRepository implements CsrfTokenRepository {

  private static final String CSRF_PARAMETER = "_csrf";
  private static final String CSRF_HEADER = "X-CSRF-TOKEN";

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    return new DefaultCsrfToken(CSRF_HEADER, CSRF_PARAMETER, createNewToken());
  }

  @Override
  public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
    String tokenValue = token != null ? token.getToken() : "";
    request.setAttribute(CSRF_PARAMETER, tokenValue);
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    while (request instanceof HttpServletRequestWrapper) {
      request = (HttpServletRequest) ((HttpServletRequestWrapper) request).getRequest();
    }

    CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

    if (token == null) {
      return null;
    }

    return new DefaultCsrfToken(CSRF_HEADER, CSRF_PARAMETER, token.getToken());
  }

  private String createNewToken() {
    return UUID.randomUUID().toString();
  }
  
}
