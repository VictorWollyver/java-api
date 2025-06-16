package com.wollyver.java_api.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
  private static final String JWTSecret = "estudos-de-java-estou-estudando-java-jwt-secret-grande";
  private static final Key key = new SecretKeySpec(JWTSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
  private static final long EXPIRATION_TIME = 86400000;

  public static String generateToken(String name) {
    return Jwts.builder().setSubject(name).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public static String getUsernameFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
  }

  public static boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
