package com.ers.dao;

import com.ers.model.Reimbursement;
import com.ers.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CRUD Operations
public class ReimbursementDaoImpl implements IReimbursementDao{
     JDBCUtil jdbcUtil;
    public ReimbursementDaoImpl()
    {
        this.jdbcUtil = new JDBCUtil();
    }


    @Override
    public boolean addReimbursement(Reimbursement r) {
        String sql = "INSERT INTO reimbursement(claim_id, reimbursed_amount, payment_mode, transaction_ref, reimbursement_date, processed_by, status) VALUES(?,?,?,?,CURDATE(),1,'PAID')";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, r.getClaimId());
            ps.setDouble(2, r.getReimbursedAmount());
            ps.setString(3, r.getPaymentMode());
            ps.setString(4, r.getTransactionRef());
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false; }
    }

    @Override
    public Reimbursement getReimbursementByClaimId(int claimId) {

        String sql = "SELECT * FROM reimbursement WHERE claim_id=?";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, claimId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapRow(rs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {

        List<Reimbursement> list = new ArrayList<>();
        String sql = "SELECT * FROM reimbursement";
        try(Connection con = jdbcUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                list.add(mapRow(rs));
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;

    }

    private Reimbursement mapRow(ResultSet rs) throws SQLException {
        Reimbursement r = new Reimbursement();
        r.setReimbursementId(rs.getInt("reimbursement_id"));
        r.setClaimId(rs.getInt("claim_id"));
        r.setReimbursedAmount(rs.getDouble("reimbursed_amount"));
        r.setPaymentMode(rs.getString("payment_mode"));
        r.setTransactionRef(rs.getString("transaction_ref"));
        if(rs.getDate("reimbursement_date") != null){
            r.setReimbursementDate(rs.getDate("reimbursement_date").toLocalDate());
        }
        r.setProcessedBy(rs.getInt("processed_by"));
        r.setStatus(rs.getString("status"));
        return r;
    }
}
