package com.ers.dao;

import com.ers.model.ExpenseClaim;

import java.util.List;

public interface IExpenseClaimDao {

    ExpenseClaim addExpenseClaim(ExpenseClaim claim);
    ExpenseClaim getExpenseClaimById(int claimId);
    List<ExpenseClaim> getClaimsByEmployeeId(int empId);
    List<ExpenseClaim> getAllClaims();
    boolean updateClaimStatus(int claimId, String status, String reason);
}

