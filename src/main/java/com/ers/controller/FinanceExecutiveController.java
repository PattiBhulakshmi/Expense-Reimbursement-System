package com.ers.controller;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;
import com.ers.service.FinanceExecutiveServiceImpl;
import com.ers.service.IFinanceExecutiveService;

import java.util.List;

public class FinanceExecutiveController {
    private IFinanceExecutiveService financeExecutiveService;

    public FinanceExecutiveController(){
        this.financeExecutiveService =new FinanceExecutiveServiceImpl();
    }

    public FinanceExecutive addNewFinanceExecutive(FinanceExecutive fin){
        return financeExecutiveService.addFinanceExecutive(fin);
    }

    public FinanceExecutive getFinanceExecutiveById(int employeeId){
        return financeExecutiveService.getFinanceExecutiveById(employeeId);
    }

    public List<FinanceExecutive> getAllFinanceExecutives(){
        return financeExecutiveService.getAllFinanceExecutives();
    }

    public List<ExpenseClaim> getPendingClaims(){
        return financeExecutiveService.getPendingClaims();
    }

    public boolean approveClaim(int claimId, int financeId){
        return financeExecutiveService.approveClaim(claimId, financeId);
    }

    public boolean processReimbursement(int claimId, int financeId, double amount){
        return financeExecutiveService.processReimbursement(claimId, financeId, amount);
    }
}
