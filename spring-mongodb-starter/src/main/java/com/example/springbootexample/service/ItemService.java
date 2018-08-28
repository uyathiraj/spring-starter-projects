package com.example.springbootexample.service;

import com.example.springbootexample.model.Item;
import com.example.springbootexample.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<ItemRepository, Item> {

}
