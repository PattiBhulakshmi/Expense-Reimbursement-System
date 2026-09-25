package com.ers.dao;

import com.ers.model.ExpenseCategory;

import java.util.List;

public interface IExpenseCategoryDao {
    ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory);
    ExpenseCategory getExpenseCategoryById(int categoryId);
    List<ExpenseCategory> getAllExpenseCategories();

}
