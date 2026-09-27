package com.ers.service;

import com.ers.dao.ClaimItemDaoImpl;
import com.ers.dao.IClaimItemDao;
import com.ers.exception.ClaimItemAddedException;
import com.ers.exception.ClaimItemNotFoundException;
import com.ers.model.ClaimItem;

import java.util.List;

public class ClaimItemServiceImpl implements IClaimItemService {
    IClaimItemDao claimItemDao;

    public ClaimItemServiceImpl() {

        this.claimItemDao=new ClaimItemDaoImpl();
    }
   // Testing mock object purpose
    public ClaimItemServiceImpl(IClaimItemDao claimItemDao) {

        this.claimItemDao = claimItemDao;
    }

    @Override
    public boolean addClaimItem(ClaimItem claimItem) {
        boolean status = claimItemDao.addClaimItem(claimItem);
        if (!status) {
            throw new ClaimItemAddedException(
                    "Failed to add claim item"
            );
        }

        return true;
    }

    @Override
    public List<ClaimItem> getClaimItemsByClaimId(int claimId) {

        List<ClaimItem> claimItems =
                claimItemDao.getClaimItemsByClaimId(claimId);

        if (claimItems == null || claimItems.isEmpty()) {
            throw new ClaimItemNotFoundException(
                    "No claim items found for claim ID: " + claimId
            );
        }

        return claimItems;

    }


}
