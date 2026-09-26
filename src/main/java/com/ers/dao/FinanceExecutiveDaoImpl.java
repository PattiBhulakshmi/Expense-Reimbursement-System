package com.ers.dao;

import com.ers.model.ExpenseClaim;
import com.ers.model.FinanceExecutive;
import com.ers.model.Reimbursement;
import com.ers.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FinanceExecutiveDaoImpl implements IFinanceExecutiveDao{

    JDBCUtil jdbcUtil;

    public FinanceExecutiveDaoImpl(){
        this.jdbcUtil=new JDBCUtil();
    }

    @Override
    public FinanceExecutive addFinanceExecutive(FinanceExecutive fin) {
        String sql = "INSERT INTO finance_executive( employee_id,full_name, email,department) VALUES(?,?,?,?)";
        System.out.println("DEBUG SQL: Inserting ID=" + fin.getEmployeeId());
        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, fin.getEmployeeId());
            ps.setString(2, fin.getFullName());
            ps.setString(3, fin.getEmail());
            ps.setString(4, fin.getDepartment());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return fin;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public FinanceExecutive getFinanceExecutiveById(int employeeId) {
        String sql = "SELECT * FROM finance_executive WHERE employee_id=?";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                FinanceExecutive fe = new FinanceExecutive();
                fe.setEmployeeId(rs.getInt("employee_id"));
                fe.setFullName(rs.getString("full_name"));
                fe.setEmail(rs.getString("email"));
                fe.setDepartment(rs.getString("department"));
                return fe;
            }
        }catch(Exception e){ e.printStackTrace(); }
        return null;
    }

    @Override
    public List<FinanceExecutive> getAllFinanceExecutives() {
        List<FinanceExecutive> list = new ArrayList<>();
        String sql = "SELECT * FROM finance_executive";
        try(Connection con = jdbcUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                FinanceExecutive fe = new FinanceExecutive();
                fe.setEmployeeId(rs.getInt("employee_id"));
                fe.setFullName(rs.getString("full_name"));
                fe.setEmail(rs.getString("email"));
                fe.setDepartment(rs.getString("department"));
                list.add(fe);
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    @Override
    public List<ExpenseClaim> getPendingClaims() {

        List<ExpenseClaim> list = new ArrayList<>();
        String sql = "SELECT * FROM expense_claim WHERE status='PENDING'";
        try(Connection con = jdbcUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                ExpenseClaim c = new ExpenseClaim();
                c.setClaimId(rs.getInt("claim_id"));
                c.setEmployeeId(rs.getInt("employee_id"));
                c.setClaimAmount(rs.getDouble("amount"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;

    }

    @Override
    public boolean approveClaim(int claimId, int financeId) {
        String sql = "UPDATE expense_claim SET status='APPROVED', finance_id=? WHERE claim_id=?";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, financeId);
            ps.setInt(2, claimId);
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false; }
    }

    @Override
    public boolean processReimbursement(int claimId, int financeId, double amount) {
        String sql = "INSERT INTO reimbursement(claim_id, finance_id, amount, status, payment_date) VALUES(?,?,?, 'PAID', NOW())";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, claimId);
            ps.setInt(2, financeId);
            ps.setDouble(3, amount);
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false; }
    }
}
