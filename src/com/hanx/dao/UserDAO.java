package com.hanx.dao;

import com.hanx.entity.User;
import com.hanx.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // 查询所有用户信息
    public static List<User> findAll() {

        List<User> list = new ArrayList<>();
        // SQL语句
        String sql = "select userID, userName, userPassword from tb_user";

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();) {

            // 6. 遍历结果集
            while (rs.next()) {

                User user = new User();
                user.setUserID(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));

                list.add(user);

            }

        } catch (SQLException e) {
            System.out.println("数据库查询过程中出现问题...");
        }

        return list;
    }

    public static User findUser(Integer userID) {
        User userQueried = new User();

        // SQL语句
        String sql = "select userID, userName, userPassword from tb_user where userID = " + userID;

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();
        ){

            // 6. 遍历结果集
            if (rs.next()) {
                userQueried.setUserID(rs.getInt("userID"));
                userQueried.setUserName(rs.getString("userName"));
                userQueried.setUserPassword(rs.getString("userPassword"));
            }else{
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRORHERE...");
        }

        return userQueried;
    }
}
