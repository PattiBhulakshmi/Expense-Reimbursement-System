package com.ers.controller;

import com.ers.model.ExpenseCategory;
import com.ers.service.IExpenseCategoryService;

import java.util.List;

public class ExpenseCategoryController {
    private IExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(IExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return null;
    }
    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        return false;
    }
    public ExpenseCategory getExpenseCategoryById(int categoryId) {
        return null;
    }
    public List<ExpenseCategory> getAllExpenseCategories() {
        return null;
    }
    public boolean deleteExpenseCategoryById(int categoryId) {
        return false;
    }
}
