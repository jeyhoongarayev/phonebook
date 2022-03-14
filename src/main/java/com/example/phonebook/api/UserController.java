package com.example.phonebook.api;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.services.UsersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Repository
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UsersServices usersServices;

    @GetMapping
    public List<User> getAllUsers(@PathVariable List<String> userId){
         return usersServices.getAll();
    }

    @PostMapping
    public User create(@RequestBody User user ) {
        return usersServices.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return usersServices.update(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId){
         usersServices.delete(userId);
    }

    @GetMapping("/{userId}")
    public void getUser(@PathVariable String userId){ usersServices.get(userId); }
}
