package ru.goryachev.multichief.auth.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthException extends AuthenticationException {

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public JwtAuthException (String message, HttpStatus httpStatus) {
        super (message);
        this.httpStatus = httpStatus;
    }

    public JwtAuthException (String message) {
        super (message);
    }

}
