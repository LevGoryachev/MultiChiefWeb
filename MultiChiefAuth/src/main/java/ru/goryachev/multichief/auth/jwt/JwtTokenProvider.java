package ru.goryachev.multichief.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    private String secretKey;
    private long validityPeriod; //milliseconds

    public String createToken (String app_user_name, String role) {
        Claims claims = Jwts.claims().setSubject(app_user_name);
        claims.put("role", role);
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 100 * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, "gelsingforse")
                .compact();
    }

}
