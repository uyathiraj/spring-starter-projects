package com.example.springbootexample.service;

import com.example.springbootexample.exception.AppException;
import com.example.springbootexample.response.ErrorResponse;
import com.example.springbootexample.model.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseService<R extends MongoRepository,T extends BaseModel> {
    private static Logger logger = LogManager.getLogger(BaseService.class);

    @Autowired
    R repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<T> getById(String id){
        return repository.findById(id);

    }

    public T saveEntity(T entity){
        preCreate(entity);
       return (T)repository.save(entity);
    }

    public T updateEntity(T entity) throws AppException {
        Optional<T> saved = repository.findById(entity.getId());
        if(!saved.isPresent()){
            throw new AppException(new ErrorResponse("Entity not found"));
        }
        T savedEntity = saved.get();
        preUpdate(savedEntity);

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map =  mapper.convertValue(entity, Map.class);

        Query query = new Query().addCriteria(Criteria.where("id").is(savedEntity.getId()));

        Update update = new Update();
        map.remove("id");
        map.forEach((key,value)->update.set(key,value));

        UpdateResult updateResult = mongoTemplate.updateMulti(query,update,entity.getClass());


        return saved.get();
    }

    public void deleteEntity(String id) throws AppException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new AppException(new ErrorResponse("Entity Not Found"));
    }

    public List<T> getAll(){
       return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }


    protected void preCreate(T entity){
        entity.setCreatedAt(new Date());
    }
    protected void preUpdate(T entity){
        entity.setUpdatedAt(new Date());
    }
}
