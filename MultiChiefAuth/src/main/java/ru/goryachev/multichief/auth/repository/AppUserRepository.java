package ru.goryachev.multichief.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goryachev.multichief.auth.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository <AppUser, Long> {
    Optional<AppUser> findByLogin(String email);
}
