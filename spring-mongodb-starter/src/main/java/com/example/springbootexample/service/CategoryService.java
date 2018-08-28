package com.example.springbootexample.service;

import com.example.springbootexample.model.Item;
import com.example.springbootexample.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<CategoryRepository, Item> {
}
