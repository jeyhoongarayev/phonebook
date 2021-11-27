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

    private static String SUCCESS_MESSAGE = "OK";
    private static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private static int SUCCESS_CODE = 200;
    private static int NOT_FOUND_CODE = 404;
    private final UserRepository userRepository;

    @Override
    public ResponseData<User> create(User user) {
        User us = userRepository.save(user);

        return GenerateResponseUtility.userFunc.generate(SUCCESS_CODE, SUCCESS_MESSAGE, us);
    }

    @Override
    public ResponseData<User> update(User user) {
        User findUser = userRepository.findByUserIdAndDeletedIsFalse(user.getUserId());
        if (findUser != null) {
            User updatedUser = userRepository.save(user);
            return GenerateResponseUtility.userFunc.generate(SUCCESS_CODE, SUCCESS_MESSAGE, updatedUser);
        }
            return GenerateResponseUtility.userFunc.generate(NOT_FOUND_CODE, NOT_FOUND_MESSAGE, null);

    }

    public ResponseData<List<User>> getAll() {
        List<User>  listOfUsers= userRepository.findAllByDeletedIsFalse();
        if(listOfUsers.isEmpty()){
            return GenerateResponseUtility.userListFunc.generate(NOT_FOUND_CODE, NOT_FOUND_MESSAGE, listOfUsers);

        }
        return GenerateResponseUtility.userListFunc.generate(SUCCESS_CODE, SUCCESS_MESSAGE, listOfUsers);
    }

    public ResponseData<String> delete(String userId){
        User user = userRepository.findByUserIdAndDeletedIsFalse(userId);
        if(user!=null){
            user.setDeleted(true);
            userRepository.save(user);
            return ResponseData.<String>builder()
                    .code(200)
                    .message("success")
                    .body("delete success")
                    .build();

        }
        return ResponseData.<String >builder()
                .code(404)
                .message("not_found")
                .body("delete failed")
                .build();
    }

    }




