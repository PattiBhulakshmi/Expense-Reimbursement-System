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
    public boolean addExpenseClaim(ExpenseClaim claim) {

        String sql = "INSERT INTO expense_claim (employee_id, claim_desc, claim_amount, claim_date, status, document_path) VALUES (?,?,?,?,?,?)";
        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, claim.getEmployeeId());
            ps.setString(2, claim.getClaimDesc());
            ps.setDouble(3, claim.getClaimAmount());
            ps.setDate(4, Date.valueOf(claim.getClaimDate()));
            ps.setString(5, claim.getStatus() == null ? "PENDING" : claim.getStatus());
            ps.setString(6, claim.getDocumentPath());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ExpenseClaim getExpenseClaimById(int claimId) {
        ExpenseClaim claim = null;
        String sql = "SELECT * FROM expense_claim WHERE claim_id = ?";

        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, claimId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                claim = new ExpenseClaim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setEmployeeId(rs.getInt("employee_id"));
                claim.setClaimDesc(rs.getString("claim_desc"));
                claim.setClaimAmount(rs.getDouble("claim_amount"));

                java.sql.Date sqlDate = rs.getDate("claim_date");
                if (sqlDate != null) {
                    claim.setClaimDate(sqlDate.toLocalDate());
                }

                claim.setStatus(rs.getString("status"));
                claim.setDocumentPath(rs.getString("document_path"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return claim;
    }

    @Override
    public List<ExpenseClaim> getClaimsByEmployeeId(int employeeId) {
        List<ExpenseClaim> list = new ArrayList<>();
        String sql = "SELECT * FROM expense_claim WHERE employee_id = ?";

        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExpenseClaim claim = new ExpenseClaim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setEmployeeId(rs.getInt("employee_id"));
                claim.setClaimDesc(rs.getString("claim_desc"));
                claim.setClaimAmount(rs.getDouble("claim_amount"));

                java.sql.Date sqlDate = rs.getDate("claim_date");
                if (sqlDate != null) {
                    claim.setClaimDate(sqlDate.toLocalDate());
                }
                claim.setStatus(rs.getString("status"));
                claim.setDocumentPath(rs.getString("document_path"));

                list.add(claim); // IMPORTANT - add to list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ExpenseClaim> getAllClaims() {

        List<ExpenseClaim> list = new ArrayList<>();
        String sql = "SELECT * FROM expense_claim";
        try (Connection con = jdbcUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ExpenseClaim claim = new ExpenseClaim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setEmployeeId(rs.getInt("employee_id"));
                claim.setClaimDesc(rs.getString("claim_desc"));
                claim.setClaimAmount(rs.getDouble("claim_amount"));

                java.sql.Date sqlDate = rs.getDate("claim_date");
                if (sqlDate != null) {
                    claim.setClaimDate(sqlDate.toLocalDate());
                }
                claim.setStatus(rs.getString("status"));
                claim.setDocumentPath(rs.getString("document_path"));

                list.add(claim); // IMPORTANT - add to list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public boolean updateClaimStatus(int claimId, String status) {
        String sql = "UPDATE expense_claim SET status = ? WHERE claim_id = ?";
        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, claimId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    private ExpenseClaim mapRow(ResultSet rs) throws SQLException {
        ExpenseClaim c = new ExpenseClaim();
        c.setClaimId(rs.getInt("claim_id"));
        c.setEmployeeId(rs.getInt("employee_id"));
        c.setClaimDesc(rs.getString("claim_desc"));
        c.setClaimAmount(rs.getDouble("claim_amount"));
        if (rs.getDate("claim_date") != null) {
            c.setClaimDate(rs.getDate("claim_date").toLocalDate());
        }
        c.setStatus(rs.getString("status"));
        c.setDocumentPath(rs.getString("document_path"));
        return c;
    }

}
