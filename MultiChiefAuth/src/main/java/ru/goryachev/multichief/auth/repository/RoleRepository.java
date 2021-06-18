package ru.goryachev.multichief.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goryachev.multichief.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Long> {
}
