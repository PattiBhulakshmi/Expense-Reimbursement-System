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
    public ExpenseClaim addExpenseClaim(ExpenseClaim claim) {
        return expenseClaimDao.addExpenseClaim(claim); }
    @Override
    public ExpenseClaim getExpenseClaimById(int claimId) {
        return expenseClaimDao.getExpenseClaimById(claimId); }
    @Override
    public List<ExpenseClaim> getClaimsByEmployeeId(int empId) {
        return expenseClaimDao.getClaimsByEmployeeId(empId); }
    @Override
    public List<ExpenseClaim> getAllClaims() {
        return expenseClaimDao.getAllClaims(); }
    @Override
    public boolean updateClaimStatus(int claimId, String status, String reason) {
        return expenseClaimDao.updateClaimStatus(claimId, status, reason); }
}
