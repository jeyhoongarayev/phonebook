package com.example.phonebook.services;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.model.ResponseData;

import java.util.List;

public interface UsersServices {
    ResponseData<User> create(User user);

    ResponseData<User> update(User user);

    ResponseData<List<User>> get(List<String> userId);
}
