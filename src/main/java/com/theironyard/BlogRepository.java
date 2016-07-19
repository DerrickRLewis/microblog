package com.theironyard;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface BlogRepository extends CrudRepository<Message,Integer > {


    @Override
    List<Message> findAll();

//    @Query
//List<Message>updateMessage();


}


