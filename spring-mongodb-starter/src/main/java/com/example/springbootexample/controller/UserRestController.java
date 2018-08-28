package com.example.springbootexample.controller;

import com.example.springbootexample.model.AppUser;
import com.example.springbootexample.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController extends BaseRestController<UserService, AppUser> {

}
