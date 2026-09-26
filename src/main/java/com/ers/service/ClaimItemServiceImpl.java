package com.ers.service;

import com.ers.dao.ClaimItemDaoImpl;
import com.ers.dao.IClaimItemDao;
import com.ers.model.ClaimItem;

import java.util.List;

public class ClaimItemServiceImpl implements IClaimItemService {
    IClaimItemDao claimItemDao;

    public ClaimItemServiceImpl() {
       this.claimItemDao=new ClaimItemDaoImpl();
    }

    @Override
    public boolean addClaimItem(ClaimItem claimItem) {
        return claimItemDao.addClaimItem(claimItem);
    }

    @Override
    public List<ClaimItem> getClaimItemsByClaimId(int claimId) {

        return claimItemDao.getClaimItemsByClaimId(claimId);
    }


}
