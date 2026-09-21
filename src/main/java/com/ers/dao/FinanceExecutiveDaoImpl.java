package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;

import java.util.List;

public class FinanceExecutiveDaoImpl implements IFinanceExecutiveDao{
    @Override
    public FinanceExecutive addFinanceExecutive(FinanceExecutive financeExecutive) {
        return null;
    }

    @Override
    public boolean updateFinanceExecutive(FinanceExecutive financeExecutive) {
        return false;
    }

    @Override
    public FinanceExecutive getFinanceExecutiveById(int employeeId) {
        return null;
    }

    @Override
    public List<FinanceExecutive> getAllFinanceExecutives() {
        return List.of();
    }

    @Override
    public boolean deleteFinanceExecutiveById(int employeeId) {
        return false;
    }

    @Override
    public List<ExpenseClaim> getPendingClaims() {
        return List.of();
    }

    @Override
    public ExpenseClaim getClaimById(int claimId) {
        return null;
    }

    @Override
    public boolean processPayment(int claimId, int financeExecutiveId, String paymentMode) {
        return false;
    }

    @Override
    public List<Reimbursement> getReimbursementHistory(int financeExecutiveId) {
        return List.of();
    }
}
