package com.ers.controller;

import com.ers.model.Reimbursement;
import com.ers.service.IReimbursementService;
import com.ers.service.ReimbursementServiceImpl;

import java.util.List;

public class ReimbursementController {
    private IReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementServiceImpl();
    }

    public boolean payClaim(Reimbursement r){

        return reimbursementService.addReimbursement(r);
    }

    public Reimbursement checkMyPayment(int claimId){
        return reimbursementService.getReimbursementByClaimId(claimId);
    }

    public List<Reimbursement> viewAllPayments(){
        return reimbursementService.getAllReimbursements();
    }
}
