package com.ers.controller;

import com.ers.model.Reimbursement;
import com.ers.service.IReimbursementService;

import java.util.List;

public class ReimbursementController {
    private IReimbursementService reimbursementService;

    public ReimbursementController(IReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    public Reimbursement addReimbursement(Reimbursement reimbursement) {
        return null;
    }
    public boolean updateReimbursement(Reimbursement reimbursement) {
        return false;
    }
    public Reimbursement getReimbursementById(int reimbursementId) {
        return null;
    }
    public List<Reimbursement> getAllReimbursements() {
        return null;
    }
    public boolean deleteReimbursementById(int reimbursementId) {
        return false;
    }
    public Reimbursement getReimbursementByClaimId(int claimId) {
        return null;
    }
    public List<Reimbursement> getReimbursementsByEmployeeId(int employeeId) {
        return null;
    }
    public List<Reimbursement> getReimbursementsByStatus(String status) {
        return null;
    }
}
