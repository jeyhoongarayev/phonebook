package com.example.phonebook.util;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.model.ResponseData;

import java.util.List;

public class GenerateResponseUtility {

    public static GenerateResponse<Integer, String, User, ResponseData<User>> userFunc = (code, message, data) ->
            ResponseData.<User>builder()
                    .code(code)
                    .message(message)
                    .body(data)
                    .build();

    public static GenerateResponse<Integer, String, List<User>, ResponseData<List<User>>> userListFunc = (code, message, list) ->
            ResponseData.<List<User>>builder()
                    .code(code)
                    .message(message)
                    .body(list)
                    .build();
}
