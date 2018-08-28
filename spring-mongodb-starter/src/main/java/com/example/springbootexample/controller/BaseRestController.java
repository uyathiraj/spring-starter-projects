package com.example.springbootexample.controller;


import com.example.springbootexample.exception.AppException;
import com.example.springbootexample.response.SuccessResponse;
import com.example.springbootexample.model.BaseModel;
import com.example.springbootexample.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public abstract class BaseRestController<S extends BaseService, T extends BaseModel> {

    @Autowired
    S service;

    @GetMapping("/{id}")
    public ResponseEntity getEntity(@PathVariable("id") String id) {
        Optional<T> optionalEntity = service.getById(id);
        T entity = null;
        if (optionalEntity.isPresent()) {
            entity = optionalEntity.get();
        }
        return new ResponseEntity(new SuccessResponse<>(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEntity(@PathVariable("id") String id) throws AppException {
        service.deleteEntity(id);
        return new ResponseEntity(new SuccessResponse("Entity Deleted"), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity saveEntity(@RequestBody T entity) {

        T saved = (T) service.saveEntity(entity);
        return new ResponseEntity(new SuccessResponse<>(saved), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateEntity(@RequestBody T entity) throws AppException {
        T updated = (T) service.updateEntity(entity);
        return new ResponseEntity(new SuccessResponse<>(updated), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getAll() {
        return new ResponseEntity(new SuccessResponse<>(service.getAll()), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteAll() {
        service.deleteAll();
        return new ResponseEntity(new SuccessResponse<>("Deleted"), HttpStatus.OK);
    }
}
