package com.example.phonebook.services.impl;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.db.repository.UserRepository;
import com.example.phonebook.model.ResponseData;
import com.example.phonebook.services.UsersServices;
import com.example.phonebook.util.GenerateResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UsersServices {

    private final UserRepository userRepository;

    @Override
    public ResponseData<User> create(User user) {
        User us = userRepository.save(user);

        return GenerateResponseUtility.userFunc.generate(201, "CREATED", us);
    }

    @Override
    public ResponseData<User> update(User user) {
        return null;
    }

    @Override
    public ResponseData<List<User>> get(List<String> userId) {
        List<User> list = userRepository.findById(userId);
        if(!list.isEmpty()) {
            return GenerateResponseUtility.userListFunc.generate(200, "OK", list);
        }else {
            return GenerateResponseUtility.userListFunc.generate(404, "NOT_FOUND",null);
        }
    }



}
