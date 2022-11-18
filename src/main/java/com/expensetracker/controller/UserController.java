package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.entity.User;
import com.expensetracker.entity.UserModel;
import com.expensetracker.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    
   
    
    
    @GetMapping("/profile")
    public ResponseEntity<User> readUser()
    {
        return new ResponseEntity<User>(service.readUser(), HttpStatus.OK);
        
    }
    
    
    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user)
    {
        
        return new ResponseEntity<User>(service.update(user), HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser()
    {
        service.deleteUser();
        
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        
        
    }
    
}
