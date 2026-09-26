package com.ers.dao;

import com.ers.model.ClaimItem;
import com.ers.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClaimItemDaoImpl implements IClaimItemDao{
    JDBCUtil jdbcUtil;

    public ClaimItemDaoImpl(){

        this.jdbcUtil=new JDBCUtil();
    }
    @Override
    public boolean addClaimItem(ClaimItem item) {

        String sql = "INSERT INTO claim_item(claim_id, category_id, description, amount, expense_date) VALUES(?,?,?,?,CURDATE())";
        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, item.getClaimId());
            ps.setInt(2, item.getCategoryId());
            ps.setString(3, item.getDescription());
            ps.setDouble(4, item.getAmount());

            int rows=ps.executeUpdate();
            System.out.println("Rows inserted: "+rows);
            return rows> 0;
        } catch (Exception e) {

            System.out.println("error: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ClaimItem> getClaimItemsByClaimId(int claimId) {
        List<ClaimItem> list = new ArrayList<>();
        String sql = "SELECT * FROM claim_item WHERE claim_id = ?";
        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, claimId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClaimItem item = new ClaimItem();
                item.setItemId(rs.getInt("item_id"));
                item.setClaimId(rs.getInt("claim_id"));
                item.setCategoryId(rs.getInt("category_id"));
                item.setDescription(rs.getString("description"));
                item.setAmount(rs.getDouble("amount"));
                if (rs.getDate("expense_date") != null) {
                    item.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                }
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    }



