package ru.goryachev.multichief.auth.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.auth.entity.AppUser;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/")
    @PreAuthorize("hasAuthority('all_read')")
    public ResponseEntity<List<AppUser>> getAllUsers () {
        logger.info("AdminController: getAllUsers()");
    return null;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('all_read')")
    public ResponseEntity<AppUser> getById (@PathVariable Long id) {
        Long f = id;
        logger.info("AdminController: getById()");
        return null;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('all_create')")
    public ResponseEntity<AppUser> createUser () {
        return null;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('all_update')")
    public ResponseEntity<AppUser> updateUser (@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('all_delete')")
    public ResponseEntity<AppUser> deleteUser (@PathVariable Long id) {
        return null;
    }
}
