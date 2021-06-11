package ru.goryachev.multichief.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
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

    public boolean validateToken (String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return !claimsJws.getBody().getExpiration().before(new Date());
    }

}
