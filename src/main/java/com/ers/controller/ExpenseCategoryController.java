package com.ers.controller;

import com.ers.model.ExpenseCategory;
import com.ers.service.ExpenseCategoryServiceImpl;
import com.ers.service.IExpenseCategoryService;

import java.util.List;

public class ExpenseCategoryController {
    private IExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController() {
        this.expenseCategoryService = new ExpenseCategoryServiceImpl();
    }
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryService.addExpenseCategory(expenseCategory);
    }

    public ExpenseCategory getExpenseCategoryById(int categoryId) {
        return expenseCategoryService.getExpenseCategoryById(categoryId);
    }
    public List<ExpenseCategory> getAllExpenseCategories() {

        return expenseCategoryService.getAllExpenseCategories();
    }

}
