package com.ers.service;

import com.ers.model.Reimbursement;

import java.util.List;

public interface IReimbursementService {


    boolean addReimbursement(Reimbursement reimbursement);
    Reimbursement getReimbursementByClaimId(int claimId);
    List<Reimbursement> getAllReimbursements();
}
