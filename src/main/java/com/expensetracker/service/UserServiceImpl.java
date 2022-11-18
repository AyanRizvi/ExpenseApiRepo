package com.expensetracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.User;
import com.expensetracker.entity.UserModel;
import com.expensetracker.exceptions.ItemAlreadyExistsException;
import com.expensetracker.exceptions.ResourceNotFoundException;
import com.expensetracker.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    @Autowired
    private UserRepository repo;
    
    @Override
    public User createUser(UserModel user) {
        
        if(repo.existsByEmail(user.getEmail()))
        {
            throw new ItemAlreadyExistsException("User is already registered with email "+user.getEmail());
        }
        
        
       User newUser = new User();
  
       BeanUtils.copyProperties(user, newUser); 
       newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return repo.save(newUser);
    }

    @Override
    public User readUser() {
      Long userId = getLoggedInUser().getId();
        
        return repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not found for the id :"+userId));
        
        
    }

    @Override
    public User update(UserModel user) {
       
       User existingUser = readUser();
       existingUser.setName(user.getName() != null ? user.getName(): existingUser.getName());
       existingUser.setEmail(user.getEmail() != null ? user.getEmail(): existingUser.getEmail());
       existingUser.setPassword(user.getPassword() != null ?  bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
       existingUser.setAge(user.getAge() != null ? user.getAge(): existingUser.getAge());
       
       return  repo.save(existingUser);
       
    }

    @Override
    public void deleteUser() {
        
        User user = readUser();
        repo.delete(user);
        
    }

    @Override
    public User getLoggedInUser() {
    Authentication aunthenticate = SecurityContextHolder.getContext().getAuthentication();
    String email = aunthenticate.getName();
    
     return repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email :"+email));
        
    }

}
