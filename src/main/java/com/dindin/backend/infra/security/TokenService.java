package com.dindin.backend.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dindin.backend.models.user.User;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.secret);
      String token = JWT.create()
          .withIssuer("backend")
          .withSubject(user.getEmail())
          .sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while authenticating");
    }
  }

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(this.secret);

    try {
      return JWT.require(algorithm)
          .withIssuer("backend")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException e) {
      return null;
    }
  }
}
