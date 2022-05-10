package com.sfeir.solution3.security.user;

import com.sfeir.solution3.data.entity.SchoolUser;
import com.sfeir.solution3.data.repository.SchoolUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SchoolUserDetailsService implements UserDetailsService {

    private final SchoolUserRepository schoolUserRepository;

    public SchoolUserDetailsService(SchoolUserRepository schoolUserRepository){
        this.schoolUserRepository = schoolUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SchoolUser user = schoolUserRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new SchoolUserDetails(user);
    }
}
