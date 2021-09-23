package com.sfeir.solution4.controller;

import com.sfeir.solution4.data.entity.SchoolUser;
import com.sfeir.solution4.data.repository.SchoolUserRepository;
import com.sfeir.solution4.dto.UserDTO;
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
        SchoolUser schoolUser = new SchoolUser()
                .withName(userDTO.getName())
                .withPassword(encoder.encode(userDTO.getPassword()))
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
        var passwordToEncode = user.getPassword();
        var splitPassword = passwordToEncode.split("\\{noop\\}");
        var isPlainTextPassword = splitPassword.length == 2;
        if (isPlainTextPassword) {
            var plainTextPassword = splitPassword[1];
            var encodedPassword = encoder.encode(plainTextPassword);
            user.setPassword(encodedPassword);
        }
    }
}
