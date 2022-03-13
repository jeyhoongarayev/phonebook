package com.example.phonebook.services;

import com.example.phonebook.db.entites.User;

import java.util.List;

public interface UsersServices {
    User get(String userId);

    User create(User user);

    User update(User user);

    void delete(String userId);
    List<User> getAll();
}
