package br.com.vehiclessales.demo.domain.controller;

import br.com.vehiclessales.demo.domain.entities.User;
import br.com.vehiclessales.demo.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Rota pública! Não precisa de autenticação!
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}