package com.ers.dao;

import com.ers.model.ClaimItem;

import java.util.List;

public interface IClaimItemDao {
    boolean addClaimItem(ClaimItem claimItem);
    List<ClaimItem> getClaimItemsByClaimId(int claimId);
}
