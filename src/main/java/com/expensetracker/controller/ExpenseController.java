package com.expensetracker.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseService;

@CrossOrigin(origins = "*")
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService service;


	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page)
	{
		return service.getAllExpenses(page).toList();	
	}
	
	
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id)
	{
		return service.getExpenseById(id);
	}
	
	
	@GetMapping("/expenses/category")
	public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page)
	{
	    
	   return service.readByCategory(category, page);
	}
	
	
	@GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page)
    {
        
       return service.readByName(keyword, page);
    }
	
	
	
	@GetMapping("/expenses/date")
	
	public List<Expense> getExpensesByDates(@RequestParam(required = false) java.sql.Date startDate,
	                                        @RequestParam(required = false) java.sql.Date endDate, Pageable page)
	{
	    
	    return service.readByDate(startDate, endDate, page);
	}
	
	
	
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id)
	{
		service.deleteExpenseById(id);
	}

	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public  Expense saveExpenseDetails(@Valid @RequestBody Expense expense)
	{
		return service.saveExpenseDetails(expense);
	}

	
	
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense,@PathVariable Long id)
	{
	   return service.updateExpenseDetails(id, expense);
	}
	
}
