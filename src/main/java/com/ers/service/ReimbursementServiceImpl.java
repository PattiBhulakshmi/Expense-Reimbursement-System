package com.ers.service;

import com.ers.dao.IReimbursementDao;
import com.ers.dao.ReimbursementDaoImpl;
import com.ers.model.Reimbursement;

import java.util.List;

public class ReimbursementServiceImpl implements IReimbursementService{

    private IReimbursementDao reimbursementDao;
    public ReimbursementServiceImpl(){

        this.reimbursementDao = new ReimbursementDaoImpl();
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.addReimbursement(reimbursement);
    }

    @Override
    public Reimbursement getReimbursementByClaimId(int claimId) {
        return reimbursementDao.getReimbursementByClaimId(claimId);
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDao.getAllReimbursements();
    }
}
