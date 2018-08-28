package com.example.springbootexample.security;


import com.example.springbootexample.model.AppUser;
import com.example.springbootexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /**
         * Change by actual user implemtaion
         */
        AppUser appUser = userService.findByUsername(s);
        AppUserDetails appUserDetails = new AppUserDetails();
        //String username = "admin";
        //String password = "$2a$10$SGe4Q5F0oBPlgNQfzV8e2uO5gK/JhMaplsh0QXvYZdGSB0LKi1WTq";
        String username = appUser.getUsername();
        String password = appUser.getPassword();
       /* Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        appUser.setRoles(roles);*/
        User user = new User(username,password,getAuthority(appUser));
        appUserDetails.setUser(user);
        return appUserDetails;
    }

    private Set getAuthority(AppUser user){
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toString())));
        return authorities;
    }

}
