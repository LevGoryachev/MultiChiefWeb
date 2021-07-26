package ru.goryachev.multichief.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.goryachev.multichief.auth.entity.Role;
import ru.goryachev.multichief.auth.service.AppUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Component
@PropertySource("classpath:application.properties")
public class JwtTokenProvider {

    private AppUserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long validityPeriod; //milliseconds

    @Autowired
    public JwtTokenProvider(@Qualifier("appUserDetailsService") AppUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected void init (){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken (String app_user_name, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(app_user_name);
        claims.put("roles", roles);
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + validityPeriod * 10000);

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
        String c = token;
        UserDetails userDetails = userDetailsService.loadUserByUsername(getAppUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getAppUserName (String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return  bearerToken.substring(7, bearerToken.length());
        }
        return null;

        //return request.getHeader("authorizationHeader");
    }
}
