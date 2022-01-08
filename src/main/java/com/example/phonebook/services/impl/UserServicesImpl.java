package com.example.phonebook.services.impl;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.db.repository.UserRepository;
import com.example.phonebook.services.UsersServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UsersServices {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        User us = userRepository.save(user);

        return us;
    }

    @Override
    public User update(User user) {
        User findUser = userRepository.findByUserIdAndDeletedIsFalse(user.getUserId());
        if (findUser != null) {
            User updatedUser = userRepository.save(user);
            return updatedUser;
        }
        return null;
    }


    @Override
    public void delete(String userId) {
        User deleteUser = userRepository.findByUserIdAndDeletedIsFalse(userId);
        if (deleteUser != null){
            deleteUser.setDeleted(true);
            userRepository.save(deleteUser);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> listOfUsers = userRepository.findAllByDeletedIsFalse();
        if (listOfUsers.isEmpty()) {
            return Collections.emptyList();
        }
        return listOfUsers;
    }
    }




