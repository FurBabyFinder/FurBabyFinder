package com.capstone.svcs;

import com.capstone.models.User;
import com.capstone.repositories.Roles;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;





@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {

    private final UsersRepository users;
    private final Roles roles;

    @Autowired
    public UserDetailsLoader(UsersRepository users, Roles roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found");
        }

        List<String> userRoles = roles.ofUserWith(username);
        return new UserWithRoles(user, userRoles);
    }
}