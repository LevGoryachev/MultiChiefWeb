package ru.goryachev.multichief.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.auth.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Long> {
    Optional<AppUser> findByLogin(String login);
}
