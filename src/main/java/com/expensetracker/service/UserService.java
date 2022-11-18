package com.expensetracker.service;

import com.expensetracker.entity.User;
import com.expensetracker.entity.UserModel;

public interface UserService {

  User  createUser(UserModel user);
  
  User readUser();
  
  User update(UserModel user);
  
  void deleteUser();
  
  
  User getLoggedInUser();
  
  
}
