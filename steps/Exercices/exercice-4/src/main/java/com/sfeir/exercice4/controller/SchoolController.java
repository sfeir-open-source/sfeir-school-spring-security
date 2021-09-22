package com.sfeir.exercice4.controller;

import com.sfeir.exercice4.data.SchoolUser;
import com.sfeir.exercice4.data.SchoolUserRepository;
import com.sfeir.exercice4.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolController {

    @Autowired
    private SchoolUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/private")
    public String privateData(UsernamePasswordAuthenticationToken user) {
        return String.format("Welcome %s!", user.getName());
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }

    @PostMapping("/register")
    public void addUser(@RequestBody UserDTO userDTO) {
        var plainTextPassword = userDTO.getPassword();
        var encodedPassword = plainTextPassword; // TODO 4. hacher le mot de passe
        SchoolUser schoolUser = new SchoolUser()
                .withName(userDTO.getName())
                .withPassword(encodedPassword)
                .withRole(userDTO.getRole());

        userRepository.save(schoolUser);
    }

    @PutMapping("/password/update")
    public void updatePasswords() {
        // Get all users
        var users = userRepository.findAll();
        // Update their passwords
        users.stream().forEach(this::updatePassword);
        // Save the updated users
        userRepository.saveAll(users);
    }

    private void updatePassword(SchoolUser user) {
        // TODO 2.
    }
}
