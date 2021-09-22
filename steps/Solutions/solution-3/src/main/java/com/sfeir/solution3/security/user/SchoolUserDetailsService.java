package com.sfeir.solution3.security.user;

import com.sfeir.solution3.data.repository.SchoolUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SchoolUserDetailsService implements UserDetailsService {

    @Autowired
    private SchoolUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new SchoolUserDetails(user);
    }
}
