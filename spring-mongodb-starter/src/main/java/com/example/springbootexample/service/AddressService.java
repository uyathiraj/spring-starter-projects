package com.example.springbootexample.service;

import com.example.springbootexample.model.Address;
import com.example.springbootexample.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<AddressRepository, Address> {
}
