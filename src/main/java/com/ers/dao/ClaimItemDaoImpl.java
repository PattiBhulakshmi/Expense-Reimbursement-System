package com.ers.dao;

import com.ers.model.ClaimItem;

import java.util.List;

public class ClaimItemDaoImpl implements IClaimItemDao{
    //add constructor for JDBCUtil object Initialization
    @Override
    public ClaimItem addClaimItem(ClaimItem claimItem) {
        return null;
    }

    @Override
    public boolean updateClaimItem(ClaimItem claimItem) {
        return false;
    }

    @Override
    public ClaimItem getClaimItemById(int itemId) {
        return null;
    }

    @Override
    public List<ClaimItem> getAllClaimItems() {
        return List.of();
    }

    @Override
    public boolean deleteClaimItemById(int itemId) {
        return false;
    }

    @Override
    public List<ClaimItem> getClaimItemsByClaimId(int claimId) {
        return List.of();
    }
}
