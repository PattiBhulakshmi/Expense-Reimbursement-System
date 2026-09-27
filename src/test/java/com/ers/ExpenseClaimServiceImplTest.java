package com.ers;

import com.ers.dao.IExpenseClaimDao;
import com.ers.model.ExpenseClaim;
import com.ers.service.ExpenseClaimServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseClaimServiceImplTest {
    @Mock
    private IExpenseClaimDao expenseClaimDao;
    @InjectMocks
    private ExpenseClaimServiceImpl expenseClaimService;

    @BeforeEach
    void setUp() {

        expenseClaimDao = mock(IExpenseClaimDao.class);

        expenseClaimService = new ExpenseClaimServiceImpl(expenseClaimDao);
    }

    @Test
    void testAddExpenseClaim() {

        ExpenseClaim claim = new ExpenseClaim();

        when(expenseClaimDao.addExpenseClaim(claim)).thenReturn(claim);

        ExpenseClaim result = expenseClaimService.addExpenseClaim(claim);

        assertNotNull(result);
        assertEquals(claim, result);

        verify(expenseClaimDao).addExpenseClaim(claim);
    }

    private void assertNotNull(ExpenseClaim result) {
    }

    @Test
    void testGetExpenseClaimById() {

        int claimId = 101;

        ExpenseClaim claim = new ExpenseClaim();

        when(expenseClaimDao.getExpenseClaimById(claimId)).thenReturn(claim);

        ExpenseClaim result = expenseClaimService.getExpenseClaimById(claimId);

        assertNotNull(result);
        assertEquals(claim, result);

        verify(expenseClaimDao).getExpenseClaimById(claimId);
    }

    @Test
    void testGetClaimsByEmployeeId() {

        int empId = 1;

        ExpenseClaim claim1 = new ExpenseClaim();
        ExpenseClaim claim2 = new ExpenseClaim();

        List<ExpenseClaim> claims = new ArrayList<>();

        claims.add(claim1);
        claims.add(claim2);

        when(expenseClaimDao.getClaimsByEmployeeId(empId)).thenReturn(claims);

        List<ExpenseClaim> result = expenseClaimService.getClaimsByEmployeeId(empId);

        assertTrue(result != null);
        assertEquals(2, result.size());
        assertEquals(claims, result);

        verify(expenseClaimDao).getClaimsByEmployeeId(empId);
    }

    @Test
    void testGetAllClaims() {

        ExpenseClaim claim1 = new ExpenseClaim();
        ExpenseClaim claim2 = new ExpenseClaim();

        List<ExpenseClaim> claims = new ArrayList<>();

        claims.add(claim1);
        claims.add(claim2);

        when(expenseClaimDao.getAllClaims()).thenReturn(claims);

        List<ExpenseClaim> result = expenseClaimService.getAllClaims();

        assertTrue(result != null);
        assertEquals(2, result.size());
        assertEquals(claims, result);

        verify(expenseClaimDao).getAllClaims();
    }

    @Test
    void testUpdateClaimStatusSuccess() {

        int claimId = 101;
        String status = "APPROVED";
        String reason = "Valid expense";

        when(expenseClaimDao.updateClaimStatus(
                claimId, status, reason)).thenReturn(true);

        boolean result =
                expenseClaimService.updateClaimStatus(claimId, status, reason);

        assertTrue(result);

        verify(expenseClaimDao).updateClaimStatus(claimId, status, reason);
    }

    @Test
    void testUpdateClaimStatusFailure() {

        int claimId = 101;
        String status = "REJECTED";
        String reason = "Invalid expense";

        when(expenseClaimDao.updateClaimStatus(claimId, status, reason)).thenReturn(false);

        boolean result = expenseClaimService.updateClaimStatus(claimId, status, reason);

        assertFalse(result);

        verify(expenseClaimDao).updateClaimStatus(claimId, status, reason);
    }
}
