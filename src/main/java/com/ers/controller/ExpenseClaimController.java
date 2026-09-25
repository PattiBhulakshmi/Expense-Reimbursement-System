package com.ers.controller;

import com.ers.model.ExpenseClaim;
import com.ers.service.ExpenseClaimServiceImpl;
import com.ers.service.IExpenseClaimService;

import java.util.List;

public class ExpenseClaimController {
    private IExpenseClaimService expenseClaimService;

    public ExpenseClaimController() {

        this.expenseClaimService = new ExpenseClaimServiceImpl();
    }

    public boolean submitClaim(ExpenseClaim claim) {
        return expenseClaimService.submitClaim(claim);
    }


    public ExpenseClaim viewClaimById(int claimId) {
        return expenseClaimService.viewClaimById(claimId);
    }

    public List<ExpenseClaim> viewMyClaims(int employeeId) {
        return expenseClaimService.viewMyClaims(employeeId);
    }

    public List<ExpenseClaim> viewAllClaims() {
        return expenseClaimService.viewAllClaims();
    }

    public boolean approveOrRejectClaim(int claimId, String status) {
        return expenseClaimService.approveOrRejectClaim(claimId, status);
    }
}
