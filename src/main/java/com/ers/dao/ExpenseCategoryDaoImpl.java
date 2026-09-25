package com.ers.dao;

import com.ers.model.ExpenseCategory;
import com.ers.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryDaoImpl implements IExpenseCategoryDao {
     JDBCUtil jdbcUtil;

    public ExpenseCategoryDaoImpl()
    {

        this.jdbcUtil = new JDBCUtil();
    }

    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        String sql = "INSERT INTO expense_category(category_name, description) VALUES (?,?)";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, expenseCategory.getCategory_name());
            ps.setString(2, expenseCategory.getDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                expenseCategory.setCategory_id(rs.getInt(1));
            return expenseCategory;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        }



    @Override
    public ExpenseCategory getExpenseCategoryById(int categoryId) {

        String sql = "SELECT * FROM expense_category WHERE category_id=?";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ExpenseCategory ec=new ExpenseCategory();
                ec.setCategory_id(rs.getInt("category_id"));
                ec.setCategory_name(rs.getString("category_name"));
                ec.setDescription(rs.getString("description"));
                return ec;
            }
        }catch(Exception e){ e.printStackTrace(); }
        return null;
    }

    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        List<ExpenseCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM expense_category";
        try (Connection con = jdbcUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ExpenseCategory cat = new ExpenseCategory();
                cat.setCategory_id(rs.getInt("category_id"));
                cat.setCategory_name(rs.getString("category_name"));
                cat.setDescription(rs.getString("description"));
                list.add(cat);
            }

            }catch(Exception e){
                e.printStackTrace();
            }
            return list;
        }

    }