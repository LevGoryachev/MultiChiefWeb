package ru.goryachev.multichief.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.goryachev.multichief.auth.entity.AppUser;
import ru.goryachev.multichief.auth.entity.Role;

import java.util.*;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

   /* @Override
    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }*/

    public static UserDetails convertFrom(AppUser appUser) {


       /* List<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(role.getAuthorities());*/

       /* List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
        for (GrantedAuthority role: roles) {
            permissions.addAll(getPermissionsIncludedInRole(role));
        }*/

        Set<GrantedAuthority> allAuthorities = new HashSet<>();

        for (Role role: appUser.getRoles()) {
            //role.getAuthorities();
            allAuthorities.addAll(role.getAuthorities());
        }


        return new org.springframework.security.core.userdetails.User(
                appUser.getLogin(),
                appUser.getPassword(),
                appUser.getStatus().equals("ACTIVE"),
                appUser.getStatus().equals("ACTIVE"),
                appUser.getStatus().equals("ACTIVE"),
                appUser.getStatus().equals("ACTIVE"),
                /*appUser.getRole().getAuthorities()*/
                allAuthorities
        );
    }
}
