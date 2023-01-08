package com.hanx.dao;

import com.hanx.entity.Blog;
import com.hanx.entity.Express;
import com.hanx.entity.Express;
import com.hanx.util.JdbcUtil;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpressDAO {
    public static Express findExpressByID(Integer expressID) {

        Express express = new Express();

        // SQL语句
        String sql = "select expressID, expressAddress, expressName, expressPhone, userID from tb_express where expressID = " + expressID;

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();
        ){

            // 6. 遍历结果集
            if (rs.next()) {
                express.setExpressID(rs.getInt("expressID"));
                express.setExpressAddress(rs.getString("expressAddress"));
                express.setExpressName(rs.getString("expressName"));
                express.setExpressPhone(rs.getString("expressPhone"));
                express.setUserID(rs.getInt("userID"));

            }else{
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRORHERE...");
        }

        return express;
    }

    public static List<Express> findAllExpress() {
        List<Express> list = new ArrayList<>();
        // SQL语句
        String sql = "select expressID, expressAddress, expressName, expressPhone, userID from tb_express";

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();) {

            // 6. 遍历结果集
            while (rs.next()) {

                Express express = new Express();
                express.setExpressID(rs.getInt("expressID"));
                express.setExpressAddress(rs.getString("expressAddress"));
                express.setExpressName(rs.getString("expressName"));
                express.setExpressPhone(rs.getString("expressPhone"));
                express.setUserID(rs.getInt("userID"));

                list.add(express);

            }

        } catch (SQLException e) {
            System.out.println("数据库查询过程中出现问题...");
        }

        return list;
    }

    public static int updateUser(Integer expressID, Integer userID) {
        // SQL语句
        int cnt = 0;
        String sql = "update tb_express set userID = ? where expressID = ? ";

        try ( // 2.创建数据库连接
              Connection conn = JdbcUtil.getConnection();
              // 3. 创建语句对象
              PreparedStatement pstm = conn.prepareStatement(sql);
        ) {
            pstm.setInt(1,userID);
            pstm.setInt(2,expressID);
            cnt = pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("数据库查询过程中出现问题...");
        }
        return cnt;
    }
}
