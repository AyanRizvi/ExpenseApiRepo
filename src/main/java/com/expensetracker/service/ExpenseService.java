package com.expensetracker.service;

import com.expensetracker.entity.Expense;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Pageable page);
    
    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);

    Expense updateExpenseDetails(Long id, Expense expense);
    
    List<Expense> readByCategory(String category,Pageable page);
    
    List<Expense> readByName(String keyword,Pageable page);

    
    List<Expense> readByDate(java.sql.Date startDate,java.sql.Date endDate,Pageable page);
}
