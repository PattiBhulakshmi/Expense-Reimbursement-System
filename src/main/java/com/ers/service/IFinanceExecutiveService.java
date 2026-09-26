package com.ers.service;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;

import java.util.List;

public interface IFinanceExecutiveService {
    FinanceExecutive addFinanceExecutive(FinanceExecutive fin);
    FinanceExecutive getFinanceExecutiveById(int employeeId);
    List<FinanceExecutive> getAllFinanceExecutives();
    List<ExpenseClaim> getPendingClaims();
    boolean approveClaim(int claimId, int financeId);
    boolean processReimbursement(int claimId, int financeId, double amount);
}
