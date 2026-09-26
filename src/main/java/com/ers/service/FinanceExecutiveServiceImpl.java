package com.ers.service;

import com.ers.dao.FinanceExecutiveDaoImpl;
import com.ers.dao.IFinanceExecutiveDao;
import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;

import java.util.List;

//Business Logic

public class FinanceExecutiveServiceImpl implements IFinanceExecutiveService {
  IFinanceExecutiveDao financeExecutiveDao;

  public FinanceExecutiveServiceImpl(){

      this.financeExecutiveDao=new FinanceExecutiveDaoImpl();
  }

    @Override
    public FinanceExecutive addFinanceExecutive(FinanceExecutive fin) {
        return financeExecutiveDao.addFinanceExecutive(fin);
    }

    @Override
    public FinanceExecutive getFinanceExecutiveById(int employeeId) {
        return financeExecutiveDao.getFinanceExecutiveById(employeeId);
    }

    @Override
    public List<FinanceExecutive> getAllFinanceExecutives() {
        return financeExecutiveDao.getAllFinanceExecutives();
    }

    @Override
    public List<ExpenseClaim> getPendingClaims() {
        return financeExecutiveDao.getPendingClaims();
    }

    @Override
    public boolean approveClaim(int claimId, int financeId) {
        return financeExecutiveDao.approveClaim(claimId, financeId);
    }

    @Override
    public boolean processReimbursement(int claimId, int financeId, double amount) {
        return financeExecutiveDao.processReimbursement(claimId, financeId, amount);
    }
}
