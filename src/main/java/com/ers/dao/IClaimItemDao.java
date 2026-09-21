package com.ers.dao;

import com.ers.model.ClaimItem;

import java.util.List;

public interface IClaimItemDao {
    ClaimItem addClaimItem(ClaimItem claimItem);
    boolean updateClaimItem(ClaimItem claimItem);
    ClaimItem getClaimItemById(int itemId);
    List<ClaimItem> getAllClaimItems();
    boolean deleteClaimItemById(int itemId);
    List<ClaimItem> getClaimItemsByClaimId(int claimId);
}
