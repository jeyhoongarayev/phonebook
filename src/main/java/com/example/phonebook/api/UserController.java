package com.example.phonebook.api;

import com.example.phonebook.db.entites.User;
import com.example.phonebook.model.ResponseData;
import com.example.phonebook.services.UsersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Repository
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UsersServices usersServices;

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<User>>>  get(@PathVariable List<String> userId){
         return ResponseEntity.ok(usersServices.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<User>> create(@RequestBody User user ) {
        return ResponseEntity.ok(usersServices.create(user));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseData<User>> update(@RequestBody User user){
        return ResponseEntity.ok(usersServices.update(user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseData<String>> delete(@PathVariable String userId){
        return ResponseEntity.ok(usersServices.delete(userId));
    }
}
