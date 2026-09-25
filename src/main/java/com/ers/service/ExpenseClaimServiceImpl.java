package com.ers.service;

import com.ers.dao.ExpenseClaimDaoImpl;
import com.ers.dao.IExpenseClaimDao;
import com.ers.model.ExpenseClaim;

import java.util.List;

public class ExpenseClaimServiceImpl implements IExpenseClaimService {
 IExpenseClaimDao expenseClaimDao;

    public ExpenseClaimServiceImpl(){
        this.expenseClaimDao=new ExpenseClaimDaoImpl();
    }

    @Override
    public boolean submitClaim(ExpenseClaim claim) {
        return expenseClaimDao.addExpenseClaim(claim);
    }

    @Override
    public ExpenseClaim viewClaimById(int claimId) {
        return expenseClaimDao.getExpenseClaimById(claimId);
    }

    @Override
    public List<ExpenseClaim> viewMyClaims(int employeeId) {
        return expenseClaimDao.getClaimsByEmployeeId(employeeId);
    }

    @Override
    public List<ExpenseClaim> viewAllClaims() {
        return expenseClaimDao.getAllClaims();
    }

    @Override
    public boolean approveOrRejectClaim(int claimId, String status) {
        return expenseClaimDao.updateClaimStatus(claimId, status);
    }
}
