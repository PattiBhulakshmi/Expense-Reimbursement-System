package com.ers;

import com.ers.dao.IFinanceExecutiveDao;
import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.service.FinanceExecutiveServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FinanceExecutiveServiceImplTest {

    private IFinanceExecutiveDao financeExecutiveDao;
    private FinanceExecutiveServiceImpl financeExecutiveService;

    @BeforeEach
    void setUp() {

        financeExecutiveDao = mock(IFinanceExecutiveDao.class);

        financeExecutiveService = new FinanceExecutiveServiceImpl(financeExecutiveDao);
    }

    @Test
    void testAddFinanceExecutive() {

        FinanceExecutive financeExecutive = new FinanceExecutive();

        when(financeExecutiveDao.addFinanceExecutive(financeExecutive))
                .thenReturn(financeExecutive);

        FinanceExecutive result = financeExecutiveService.addFinanceExecutive(financeExecutive);

        assertTrue(result != null);
        assertEquals(financeExecutive, result);

        verify(financeExecutiveDao).addFinanceExecutive(financeExecutive);
    }

    @Test
    void testGetFinanceExecutiveById() {

        int employeeId = 100;

        FinanceExecutive financeExecutive = new FinanceExecutive();

        when(financeExecutiveDao.getFinanceExecutiveById(10))
                .thenReturn(financeExecutive);

        FinanceExecutive result =
                financeExecutiveService.getFinanceExecutiveById(employeeId);

        assertTrue(result != null);
        assertEquals(financeExecutive, result);

        verify(financeExecutiveDao).getFinanceExecutiveById(employeeId);
    }

    @Test
    void testGetAllFinanceExecutives() {

        FinanceExecutive fin1 = new FinanceExecutive();

        FinanceExecutive fin2 = new FinanceExecutive();

        List<FinanceExecutive> executives = new ArrayList<>();

        executives.add(fin1);
        executives.add(fin2);

        when(financeExecutiveDao.getAllFinanceExecutives())
                .thenReturn(executives);

        List<FinanceExecutive> result = financeExecutiveService
                        .getAllFinanceExecutives();

        assertTrue(result != null);
        assertEquals(2, result.size());
        assertEquals(executives, result);

        verify(financeExecutiveDao).getAllFinanceExecutives();
    }

    @Test
    void testGetPendingClaims() {

        ExpenseClaim claim1 = new ExpenseClaim();

        ExpenseClaim claim2 = new ExpenseClaim();

        List<ExpenseClaim> claims = new ArrayList<>();

        claims.add(claim1);
        claims.add(claim2);

        when(financeExecutiveDao.getPendingClaims())
                .thenReturn(claims);

        List<ExpenseClaim> result =
                financeExecutiveService.getPendingClaims();

        assertTrue(result != null);
        assertEquals(2, result.size());
        assertEquals(claims, result);

        verify(financeExecutiveDao).getPendingClaims();
    }

    @Test
    void testApproveClaimSuccess() {

        int claimId = 101;
        int financeId = 1;

        when(financeExecutiveDao
                .approveClaim(claimId, financeId)).thenReturn(true);

        boolean result =
                financeExecutiveService.approveClaim(claimId, financeId);

        assertTrue(result);

        verify(financeExecutiveDao).approveClaim(claimId, financeId);
    }

    @Test
    void testApproveClaimFailure() {

        int claimId = 101;
        int financeId = 1;

        when(financeExecutiveDao
                .approveClaim(claimId, financeId)).thenReturn(false);

        boolean result =
                financeExecutiveService.approveClaim(claimId, financeId);

        assertFalse(result);

        verify(financeExecutiveDao).approveClaim(claimId, financeId);
    }

    @Test
    void testProcessReimbursementSuccess() {

        int claimId = 101;
        int financeId = 1;
        double amount = 5000.0;

        when(financeExecutiveDao
                .processReimbursement(
                        claimId, financeId, amount)).thenReturn(true);

        boolean result =
                financeExecutiveService
                        .processReimbursement(claimId, financeId, amount);

        assertTrue(result);

        verify(financeExecutiveDao)
                .processReimbursement(claimId, financeId, amount);
    }

    @Test
    void testProcessReimbursementFailure() {

        int claimId = 101;
        int financeId = 1;
        double amount = 5000.0;

        when(financeExecutiveDao
                .processReimbursement(
                        claimId, financeId, amount)).thenReturn(false);

        boolean result =
                financeExecutiveService
                        .processReimbursement(claimId, financeId, amount);

        assertFalse(result);

        verify(financeExecutiveDao)
                .processReimbursement(claimId, financeId, amount);
    }
}
