package com.example.springbootexample.service;

import com.example.springbootexample.model.AppUser;
import com.example.springbootexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserRepository, AppUser> {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    public AppUser findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    private void encodePassword(AppUser entity) {
        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);
    }

    @Override
    protected void preCreate(AppUser entity) {
        if (entity.getPassword() != null) {
            encodePassword(entity);
        }
        super.preCreate(entity);
    }

    @Override
    protected void preUpdate(AppUser entity) {
        if (entity.getPassword() != null) {
            encodePassword(entity);
        }
        super.preUpdate(entity);
    }

}
