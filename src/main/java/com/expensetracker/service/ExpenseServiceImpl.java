package com.expensetracker.service;

import com.expensetracker.entity.Expense;
import com.expensetracker.exceptions.ResourceNotFoundException;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class ExpenseServiceImpl  implements  ExpenseService{

    @Autowired
    private UserService userService;
    
    @Autowired
    private ExpenseRepository repo;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {

        return repo.findByUserId(userService.getLoggedInUser().getId(), page);
    }

    
	@Override
	public Expense getExpenseById(Long id) {
		
		Optional<Expense> expense=repo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
		
		if(expense.isPresent())
		{
			return expense.get();
		}
		
		throw new ResourceNotFoundException("Expense is not found for id :"+id);
	}

	@Override
	public void deleteExpenseById(Long id) {
	    
	    Expense enpense = getExpenseById(id);
		repo.delete(enpense);
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {

	    expense.setUser(userService.getLoggedInUser());
    return 	repo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
existingExpense.setName(expense.getName() != null ? expense.getName(): existingExpense.getName());
existingExpense.setAmount(expense.getAmount() !=null ? expense.getAmount():existingExpense.getAmount());
existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory():existingExpense.getCategory());
existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription():existingExpense.getDescription());
existingExpense.setDate(expense.getDate() != null ? expense.getDate():existingExpense.getDate());
	  return	repo.save(existingExpense);
	  
	}


    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        
        return repo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
    }


    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
       
        return repo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
    }


    @Override
    public List<Expense> readByDate(java.sql.Date startDate, java.sql.Date endDate, Pageable page) {
        if(startDate == null)
        {
            startDate = new java.sql.Date(0);
        }
        if(endDate == null)
        {
            endDate = new java.sql.Date(System.currentTimeMillis());
        }
        
        return repo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, page).toList();
    }
}
