package ru.goryachev.multichief.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.auth.dto.SecurityUser;
import ru.goryachev.multichief.auth.entity.AppUser;
import ru.goryachev.multichief.auth.repository.AppUserRepository;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserDetailsService (AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        return SecurityUser.convertFrom(appUser);
    }
}
