package ru.goryachev.multichief.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider {

    private UserDetails userDetails;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long validityPeriod; //milliseconds

    public JwtTokenProvider(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    protected void init (){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken (String app_user_name, String role) {
        Claims claims = Jwts.claims().setSubject(app_user_name);
        claims.put("role", role);
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + validityPeriod * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken (String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthException("Jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication (String token){
        return null;
    }

    public String getAppUserName (String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

}
