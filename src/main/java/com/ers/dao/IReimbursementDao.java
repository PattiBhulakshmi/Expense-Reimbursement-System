package com.ers.dao;

import com.ers.model.Reimbursement;

import java.util.List;

public interface IReimbursementDao {

    boolean addReimbursement(Reimbursement reimbursement);
    Reimbursement getReimbursementByClaimId(int claimId);
    List<Reimbursement> getAllReimbursements();
}
