package com.ers.service;

import com.ers.dao.ExpenseCategoryDaoImpl;
import com.ers.dao.IExpenseCategoryDao;
import com.ers.model.ExpenseCategory;

import java.util.List;

public class ExpenseCategoryServiceImpl implements IExpenseCategoryService{
    IExpenseCategoryDao expenseCategoryDao;

    public ExpenseCategoryServiceImpl() {
        this.expenseCategoryDao = new ExpenseCategoryDaoImpl();
    }


    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryDao.addExpenseCategory(expenseCategory);
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(int categoryId) {

        return expenseCategoryDao.getExpenseCategoryById(categoryId);
    }

    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {

        return expenseCategoryDao.getAllExpenseCategories();
    }
}
