package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;

import java.util.List;

public interface IFinanceExecutiveDao {
    FinanceExecutive addFinanceExecutive(FinanceExecutive fin);
    FinanceExecutive getFinanceExecutiveById(int employeeId);
    List<FinanceExecutive> getAllFinanceExecutives();
    List<ExpenseClaim> getPendingClaims();
    boolean approveClaim(int claimId, int financeId);
    boolean processReimbursement(int claimId, int financeId, double amount);
}
