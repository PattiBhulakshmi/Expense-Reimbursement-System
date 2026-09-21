package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;

import java.util.List;

public interface IFinanceExecutiveDao {
    //CRUD Operations
    FinanceExecutive addFinanceExecutive(FinanceExecutive financeExecutive);
    boolean updateFinanceExecutive(FinanceExecutive financeExecutive);
    FinanceExecutive getFinanceExecutiveById(int employeeId);
    List<FinanceExecutive> getAllFinanceExecutives();
    boolean deleteFinanceExecutiveById(int employeeId);
    //Expense Claim operations
    List<ExpenseClaim> getPendingClaims();
    ExpenseClaim getClaimById(int claimId);
    //Reimbursement operations
    boolean processPayment(int claimId, int financeExecutiveId, String paymentMode);
    List<Reimbursement> getReimbursementHistory(int financeExecutiveId);
}
