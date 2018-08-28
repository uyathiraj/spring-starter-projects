package com.example.springbootexample.controller;

import com.example.springbootexample.model.Item;
import com.example.springbootexample.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemRestController extends BaseRestController<ItemService, Item> {

}
