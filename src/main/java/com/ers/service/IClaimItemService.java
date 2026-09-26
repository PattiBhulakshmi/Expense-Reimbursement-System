package com.ers.service;

import com.ers.model.ClaimItem;

import java.util.List;

public interface IClaimItemService {
   boolean addClaimItem(ClaimItem claimItem);
    List<ClaimItem> getClaimItemsByClaimId(int claimId);
}
