package com.ers.dao;

import com.ers.model.User;
import com.ers.util.JDBCUtil;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements IUserDao{


    @Override
    public boolean registerUser(User user) {
        String sql="INSERT INTO users(user_name,password,role,is_active,created_at) VALUES(?,?,?,?,?)";
        try (
            Connection conn=new JDBCUtil().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setBoolean(4, user.isActive());
            ps.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));

            int rows= ps.executeUpdate();
            return rows>0;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public User getUserByUsername(String name) {

        String sql="SELECT * FROM users WHERE userName=?";
        try(Connection conn=new JDBCUtil().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                User user=new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setActive(rs.getBoolean("isActive"));
                Timestamp ts = rs.getTimestamp("createdAt");

                if(ts!=null){
                    user.setCreatedAt(ts.toLocalDateTime());
                }
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
