package com.ers.service;

import com.ers.model.ExpenseClaim;

import java.util.List;

public class ExpenseClaimServiceImpl implements IExpenseClaimService {
    @Override
    public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
        return null;
    }

    @Override
    public boolean updateExpenseClaim(ExpenseClaim expenseClaim) {
        return false;
    }

    @Override
    public ExpenseClaim getExpenseClaimById(int claimId) {
        return null;
    }

    @Override
    public List<ExpenseClaim> getAllExpenseClaims() {
        return List.of();
    }

    @Override
    public boolean deleteExpenseClaimById(int claimId) {
        return false;
    }

    @Override
    public List<ExpenseClaim> getClaimsByEmployeeId(int employeeId) {
        return List.of();
    }

    @Override
    public boolean submitClaim(int claimId) {
        return false;
    }

    @Override
    public boolean approveClaim(int claimId) {
        return false;
    }

    @Override
    public boolean rejectClaim(int claimId, String reason) {
        return false;
    }

    @Override
    public List<ExpenseClaim> getClaimsByStatus(String status) {
        return List.of();
    }
}
