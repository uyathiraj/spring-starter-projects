package com.example.springbootexample.controller;

import com.example.springbootexample.model.Category;
import com.example.springbootexample.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryRestController extends BaseRestController<CategoryService, Category> {
}
