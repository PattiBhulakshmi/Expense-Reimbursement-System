package com.ers.service;

import com.ers.dao.IExpenseCategoryDao;
import com.ers.model.ExpenseCategory;

import java.util.List;

public class ExpenseCategoryServiceImpl implements IExpenseCategoryService{
    IExpenseCategoryDao expenseCategoryDao;
    public ExpenseCategoryServiceImpl(IExpenseCategoryDao expenseCategoryDao){
        this.expenseCategoryDao=expenseCategoryDao;
    }
    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return null;
    }

    @Override
    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        return false;
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(int categoryId) {
        return null;
    }

    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        return List.of();
    }

    @Override
    public boolean deleteExpenseCategoryById(int categoryId) {
        return false;
    }
}
