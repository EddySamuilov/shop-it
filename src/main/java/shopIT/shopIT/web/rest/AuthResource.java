package shopIT.shopIT.web.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shopIT.shopIT.services.TokenService;

@RestController
@RequiredArgsConstructor
public class AuthResource {

  private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

  private final TokenService tokenService;

  @PostMapping("/token")
  public String generateToken(Authentication authentication) {
    LOG.debug("Token requested for user {}.", authentication.getName());

    String token = tokenService.generateToken(authentication);

    LOG.debug("Token generated {}", token);

    return token;
  }
}
