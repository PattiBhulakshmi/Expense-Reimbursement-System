package com.ers.dao;

import com.ers.model.ExpenseCategory;
import com.ers.util.JDBCUtil;

import java.util.List;

public class ExpenseCategoryDaoImpl implements IExpenseCategoryDao{
    private JDBCUtil jdbcUtil;
    private ExpenseCategoryDaoImpl(JDBCUtil jdbcUtil){
        this.jdbcUtil=jdbcUtil;
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
