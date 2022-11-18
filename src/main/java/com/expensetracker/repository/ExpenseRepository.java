package com.expensetracker.repository;

import com.expensetracker.entity.Expense;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    
    

 Page<Expense> findByUserIdAndCategory(Long UserId,String category, Pageable page);
 
 

 // Select * from tbl_expenses where name LIKE = %keyword%
 Page<Expense> findByUserIdAndNameContaining(Long UserId, String keyword, Pageable page);

 
 
 Page<Expense>  findByUserIdAndDateBetween( Long UserId, java.sql.Date startDate,java.sql.Date endDate,Pageable page);
 
 
  Page<Expense> findByUserId(Long userId,Pageable page);
  
  
 Optional<Expense> findByUserIdAndId(Long userId, Long Id);
 
 
}
