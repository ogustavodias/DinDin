package com.dindin.backend.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

  public String geCurrentToken() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getDetails() instanceof String token)
      return token;

    return null;
  }

}
