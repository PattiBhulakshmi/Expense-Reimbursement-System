package com.ers;

import com.ers.dao.IExpenseCategoryDao;
import com.ers.model.ExpenseCategory;
import com.ers.service.ExpenseCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExpenseCatogoryServiceImplTest {

    private IExpenseCategoryDao expenseCategoryDao;
    private ExpenseCategoryServiceImpl expenseCategoryService;

    @BeforeEach
    void setUp() {

        expenseCategoryDao = mock(IExpenseCategoryDao.class);

        expenseCategoryService = new ExpenseCategoryServiceImpl(expenseCategoryDao);
    }

    @Test
    void testAddExpenseCategory() {

        ExpenseCategory category = new ExpenseCategory();

        when(expenseCategoryDao.addExpenseCategory(category)).thenReturn(category);

        ExpenseCategory result = expenseCategoryService.addExpenseCategory(category);

        assertTrue(result != null);
        assertEquals(category, result);

        verify(expenseCategoryDao).addExpenseCategory(category);
    }


    @Test
    void testGetExpenseCategoryById() {

        int categoryId = 1;

        ExpenseCategory category = new ExpenseCategory();

        when(expenseCategoryDao.getExpenseCategoryById(categoryId)).thenReturn(category);

        ExpenseCategory result = expenseCategoryService.getExpenseCategoryById(categoryId);

        assertTrue(result != null);
        assertEquals(category, result);

        verify(expenseCategoryDao).getExpenseCategoryById(categoryId);
    }

    @Test
    void testGetAllExpenseCategories() {

        ExpenseCategory category1 = new ExpenseCategory();

        ExpenseCategory category2 = new ExpenseCategory();

        List<ExpenseCategory> categories = new ArrayList<>();

        categories.add(category1);
        categories.add(category2);

        when(expenseCategoryDao.getAllExpenseCategories()).thenReturn(categories);

        List<ExpenseCategory> result = expenseCategoryService.getAllExpenseCategories();

        assertTrue(result != null);
        assertEquals(2, result.size());
        assertEquals(categories, result);

        verify(expenseCategoryDao).getAllExpenseCategories();
    }
}
