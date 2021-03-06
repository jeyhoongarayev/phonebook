package com.example.phonebook.db.repository;

import com.example.phonebook.db.entites.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUserIdAndDeletedIsFalse(String userId);

    List<User> findAllByDeletedIsFalse();
}
