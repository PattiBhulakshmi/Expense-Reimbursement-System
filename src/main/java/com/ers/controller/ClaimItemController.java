package com.ers.controller;

import com.ers.model.ClaimItem;
import com.ers.service.IClaimItemService;

import java.util.List;

public class ClaimItemController {
    private IClaimItemService claimItemService;

    public ClaimItemController(IClaimItemService claimItemService) {
        this.claimItemService = claimItemService;
    }
    public ClaimItem addClaimItem(ClaimItem claimItem) {
        return null;
    }
    public boolean updateClaimItem(ClaimItem claimItem) {
        return false;
    }
    public ClaimItem getClaimItemById(int itemId) {
        return null;
    }
    public List<ClaimItem> getAllClaimItems() {
        return null;
    }
    public boolean deleteClaimItemById(int itemId)
    {
        return false;
    }
    public List<ClaimItem> getClaimItemsByClaimId(int claimId) {
        return null;
    }
}
