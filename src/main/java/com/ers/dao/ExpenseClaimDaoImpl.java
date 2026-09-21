package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.util.JDBCUtil;

import java.util.List;

public class ExpenseClaimDaoImpl implements IExpenseClaimDao{

    JDBCUtil jdbcUtil;
    public ExpenseClaimDaoImpl(JDBCUtil jdbcUtil){
        this.jdbcUtil=jdbcUtil;
    }
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
