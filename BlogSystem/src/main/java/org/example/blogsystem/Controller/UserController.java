package org.example.blogsystem.Controller;

import jakarta.validation.Valid;

import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-system/user")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity getUsers(){

        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.updateUser(id, user);

        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully!"));


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        userService.deleteUser(id);

        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully!"));

    }

    //7: get users registered in range date
    @GetMapping("/get/registered-user-by-date/{date1}/{date2}")
    public ResponseEntity getUsersRegisteredByDate(@PathVariable LocalDate date1, @PathVariable LocalDate date2 ){

        List<User> users = userService.getUsersRegisteredByDate(date1, date2);

        return ResponseEntity.status(200).body(users);
    }











}
