package com.ers.dao;

import com.ers.model.Reimbursement;
import com.ers.util.JDBCUtil;

import java.util.List;

//CRUD Operations
public class ReimbursementDaoImpl implements IReimbursementDao{
    private JDBCUtil jdbcUtil;
    public ReimbursementDaoImpl(JDBCUtil jdbcUtil){
        this.jdbcUtil = jdbcUtil;
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
