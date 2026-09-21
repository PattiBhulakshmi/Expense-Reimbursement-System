package com.ers.service;

import com.ers.dao.IReimbursementDao;
import com.ers.model.Reimbursement;

import java.util.List;

public class ReimbursementServiceImpl implements IReimbursementService{

    private IReimbursementDao reimbursementDao;
    public ReimbursementServiceImpl(IReimbursementDao reimbursementDao){
        this.reimbursementDao = reimbursementDao;
    }
    @Override
    public Reimbursement addReimbursement(Reimbursement reimbursement) {
        return null;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        return false;
    }

    @Override
    public Reimbursement getReimbursementById(int reimbursementId) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return List.of();
    }

    @Override
    public boolean deleteReimbursementById(int reimbursementId) {
        return false;
    }

    @Override
    public Reimbursement getReimbursementByClaimId(int claimId) {
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementsByEmployeeId(int employeeId) {
        return List.of();
    }

    @Override
    public List<Reimbursement> getReimbursementsByStatus(String status) {
        return List.of();
    }
}
