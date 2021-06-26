package ru.goryachev.multichief.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.auth.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository <Permission, Long> {
}
