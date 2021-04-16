package com.appCent.toDoApp.repository;

import com.appCent.toDoApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {


    User findByName(String name);


    List<User> findAll();

    List<User> findAllByNameContaining(String name);

    @Override
    Optional<User> findById(String id);
}
