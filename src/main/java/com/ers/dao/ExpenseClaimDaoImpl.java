package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseClaimDaoImpl implements IExpenseClaimDao{

    JDBCUtil jdbcUtil;
    public ExpenseClaimDaoImpl()
    {

        this.jdbcUtil=new JDBCUtil();
    }

    @Override
    public ExpenseClaim addExpenseClaim(ExpenseClaim c) {
        String sql="INSERT INTO expense_claim(employee_id, claim_desc, claim_amount,claim_date, status, document_path) VALUES(?,?,?,CURDATE(),'PENDING',?)";
        try(Connection con=jdbcUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,c.getEmployeeId());
            ps.setString(2,c.getClaimDesc());
            ps.setDouble(3,c.getClaimAmount());
            ps.setString(4,c.getDocumentPath());

            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) {
                c.setClaimId(rs.getInt(1));
            }
            System.out.println("Claim creation sucess!");
            return c;
        }catch(Exception e){e.printStackTrace(); return null;}
    }
    @Override
    public ExpenseClaim getExpenseClaimById(int id) {
        String sql="SELECT * FROM expense_claim WHERE claim_id=?";
        try(Connection con=jdbcUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery();
            if(rs.next()) return mapRow(rs);
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    @Override
    public List<ExpenseClaim> getClaimsByEmployeeId(int empId) {
        List<ExpenseClaim> list=new ArrayList<>();
        String sql="SELECT * FROM expense_claim WHERE employee_id=?";
        try(Connection con=jdbcUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,empId); ResultSet rs=ps.executeQuery();
            while(rs.next()) list.add(mapRow(rs));
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
    @Override
    public List<ExpenseClaim> getAllClaims() {
        List<ExpenseClaim> list=new ArrayList<>();
        String sql="SELECT * FROM expense_claim";
        try(Connection con=jdbcUtil.getConnection(); Statement st=con.createStatement(); ResultSet rs=st.executeQuery(sql)){
            while(rs.next()) list.add(mapRow(rs));
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
    @Override
    public boolean updateClaimStatus(int claimId, String status, String reason) {
        String sql="UPDATE expense_claim SET status=?  WHERE claim_id=?";
        try(Connection con=jdbcUtil.getConnection(); PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,status); ps.setInt(2,claimId);
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace(); return false;}
    }
    private ExpenseClaim mapRow(ResultSet rs) throws SQLException {
        ExpenseClaim c=new ExpenseClaim();
        c.setClaimId(rs.getInt("claim_id"));
        c.setEmployeeId(rs.getInt("employee_id"));
        c.setClaimDesc(rs.getString("claim_desc"));
        c.setClaimAmount(rs.getDouble("claim_amount"));
        c.setStatus(rs.getString("status"));
        c.setDocumentPath(rs.getString("document_path"));
        return c;
    }

}
