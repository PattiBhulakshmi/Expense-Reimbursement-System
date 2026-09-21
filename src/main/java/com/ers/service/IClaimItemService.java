package com.ers.service;

import com.ers.model.ClaimItem;

import java.util.List;

public interface IClaimItemService {
    ClaimItem addClaimItem(ClaimItem claimItem);
    boolean updateClaimItem(ClaimItem claimItem);
    ClaimItem getClaimItemById(int itemId);
    List<ClaimItem> getAllClaimItems();
    boolean deleteClaimItemById(int itemId);
    List<ClaimItem> getClaimItemsByClaimId(int claimId);
}
