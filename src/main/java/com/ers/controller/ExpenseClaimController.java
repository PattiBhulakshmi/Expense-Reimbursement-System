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

    public ExpenseClaim submitClaim(ExpenseClaim claim){
        return expenseClaimService.addExpenseClaim(claim);
    }
    public ExpenseClaim viewClaimById(int claimId){
        return expenseClaimService.getExpenseClaimById(claimId);
    }
    public List<ExpenseClaim> viewMyClaims(int employeeId){
        return expenseClaimService.getClaimsByEmployeeId(employeeId);
    }
    public List<ExpenseClaim> viewAllClaims(){
        return expenseClaimService.getAllClaims();
    }
    // For Approve
    public boolean approveOrRejectClaim(int claimId, String status){
        return expenseClaimService.updateClaimStatus(claimId, status, null);
    }
    // For Reject with reason - OVERLOAD - This fixes your ch==4
    public boolean approveOrRejectClaim(int claimId, String status, String reason){
        return expenseClaimService.updateClaimStatus(claimId, status, reason);
    }
}
