package com.ers.controller;

import com.ers.model.ClaimItem;
import com.ers.service.ClaimItemServiceImpl;
import com.ers.service.IClaimItemService;

import java.util.List;

public class ClaimItemController {
    public IClaimItemService claimItemService;

    public ClaimItemController(){
        this.claimItemService=new ClaimItemServiceImpl();
    }

    public boolean addItem(ClaimItem item) {
        return claimItemService.addClaimItem(item);
    }

    public List<ClaimItem> getItemsByClaimId(int claimId) {
        return claimItemService.getClaimItemsByClaimId(claimId);
    }
}
