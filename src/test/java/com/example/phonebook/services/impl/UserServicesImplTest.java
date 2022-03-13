package com.example.phonebook.services.impl;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.db.repository.UserRepository;
import com.example.phonebook.services.impl.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserServicesImpl.class})
@ExtendWith(SpringExtension.class)
class UserServicesImplTest {

    private static final String  ID = "1";
    private static final int SIZE = 1;
    private static final String NAME = "Ceyhun Qarayev";
    private static final String PHONE = "0558525528";

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServicesImpl userServiceImpl;

    private User user;

    @BeforeEach
    void setUp() {
        setUpUser();
    }

    @Test
    void getUsersThenSuccessResult() {
        // arrange
        when(userRepository.findAllByDeletedIsFalse()).thenReturn(Collections.singletonList(user));

        // act
        List<User> actualUsers = userServiceImpl.getAll();

        // assert
        assertEquals(SIZE, actualUsers.size());
        User getResult = actualUsers.get(0);
        assertEquals(NAME, getResult.getName());
        assertEquals(ID, getResult.getUserId());
        assertEquals(PHONE, getResult.getPhone());
        verify(userRepository).findAllByDeletedIsFalse();

    }
    @Test
    void getUsersThenSuccessResult2() {
        // arrange
        when(userRepository.findAllByDeletedIsFalse()).thenReturn(Collections.emptyList());

        // act
        List<User> actualUsers = userServiceImpl.getAll();

        // assert
        assertThat(actualUsers).isEmpty();
        verify(userRepository).findAllByDeletedIsFalse();
    }


    @Test
    void addUserThenSuccessResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);

        // act
        User actualAddUserResult = userServiceImpl.create(user);

        // assert
        assertEquals(ID, actualAddUserResult.getUserId());
        verify(userRepository).save(any());
    }


    @Test
    void updateUserThenSuccessResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(user);

        // act
        User actualUpdateUserResult = userServiceImpl.update(user);

        // assert
        assertEquals(ID, actualUpdateUserResult.getUserId());
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        verify(userRepository).save(any());

    }

    @Test
    void updateUserThenFailResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(null);

        // act
        User actualUpdateUserResult = userServiceImpl.update(user);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        assertNull(actualUpdateUserResult);
    }

    @Test
    void deleteUserThenSuccessResult() {
        // arrange
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(user);

        // act
        userServiceImpl.delete(ID);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        assertTrue(userRepository.findAllByDeletedIsFalse().isEmpty());
    }

    @Test
    void deleteUserThenSuccessResult2() {
        // arrange
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(null);

        // act
        userServiceImpl.delete(ID);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
    }

    private void setUpUser() {
        user = User.builder()
                .userId(ID)
                .name(NAME)
                .phone(PHONE)
                .build();
    }

    @Test
    void testGet() {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        when(this.userRepository.findByUserIdAndDeletedIsFalse((String) any())).thenReturn(user);
        assertSame(user, this.userServiceImpl.get("42"));
        verify(this.userRepository).findByUserIdAndDeletedIsFalse((String) any());
    }

}
