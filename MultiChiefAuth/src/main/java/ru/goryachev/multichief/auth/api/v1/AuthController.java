package ru.goryachev.multichief.auth.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goryachev.multichief.auth.dto.AuthenticationRequestDTO;
import ru.goryachev.multichief.auth.entity.AppUser;
import ru.goryachev.multichief.auth.entity.Role;
import ru.goryachev.multichief.auth.jwt.JwtTokenProvider;
import ru.goryachev.multichief.auth.repository.AppUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private AppUserRepository appUserRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AppUserRepository appUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate (@RequestBody AuthenticationRequestDTO requestDTO) {
        AuthenticationRequestDTO x = requestDTO;// Debugger -to delete
        logger.info("AuthController");

       try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getName(), requestDTO.getPassword()));
            AppUser user = appUserRepository.findByLogin(requestDTO.getName()).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

           Set<Role> roles = user.getRoles();// Debugger -to delete

            String token = jwtTokenProvider.createToken(requestDTO.getName(), user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", requestDTO.getName());
            response.put("token", token);
            logger.info("2 An INFO Message from LoginController");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e){
            logger.info("3 An INFO Message from LoginController");
            return new ResponseEntity<>("Invalid login/password combination", HttpStatus.FORBIDDEN);
        }

    }


    @PostMapping("/logout")
    public void authenticate (HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }

}
