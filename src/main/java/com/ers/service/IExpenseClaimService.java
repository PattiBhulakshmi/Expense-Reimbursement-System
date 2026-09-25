package com.ers.service;

import com.ers.model.ExpenseClaim;

import java.util.List;

public interface IExpenseClaimService {
    boolean submitClaim(ExpenseClaim claim);
    ExpenseClaim viewClaimById(int claimId);
    List<ExpenseClaim> viewMyClaims(int employeeId);
    List<ExpenseClaim> viewAllClaims();
    boolean approveOrRejectClaim(int claimId, String status);
}
