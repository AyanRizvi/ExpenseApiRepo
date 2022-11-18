package com.expensetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.expensetracker.entity.LoginModel;
import com.expensetracker.entity.User;
import com.expensetracker.entity.UserModel;
import com.expensetracker.security.CustomUserDetailsService;
import com.expensetracker.service.UserService;


@RestController
public class AuthController {

   
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
   
    @Autowired
    private UserService service;

    
    
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginModel loginModel) throws Exception
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));  
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
      
        
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        
    }
    
    
    


    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid  @RequestBody UserModel user)
    {
       
       return new ResponseEntity<User>(service.createUser(user), HttpStatus.CREATED);
        
    }
    
    
}
