package com.hanx.dao;

import com.hanx.entity.Blog;
import com.hanx.entity.User;
import com.hanx.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    public static int addBlog(Blog blog){
        int cnt = 0;
        String sql = "insert into tb_blog (blogContent,userID)"+
                "values(?,?)";
        try(
                Connection conn = JdbcUtil.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
        ){
//            pstm.setInt(1,blog.getBlogID());
            pstm.setString(1,blog.getBlogContent());
            pstm.setInt(2,blog.getUserID());
            cnt = pstm.executeUpdate();
        } catch(SQLException e) {
            System.out.println("数据库增加过程中出现问题...");
        }
        return cnt;
    }

    public static Blog findBlogByID(Integer blogID) {
        Blog blog = new Blog();

        // SQL语句
        String sql = "select blogID, blogContent, userID from tb_blog where blogID = " + blogID;

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();
        ){

            // 6. 遍历结果集
            if (rs.next()) {
                blog.setBlogID(rs.getInt("blogID"));
                blog.setBlogContent(rs.getString("blogContent"));
                blog.setUserID(rs.getInt("userID"));
            }else{
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRORHERE...");
        }

        return blog;

    }

    public static List<Blog> findBlogsByRange(Integer start, Integer number) {
        List<Blog> list = new ArrayList<>();
        // SQL语句
        String sql = "select blogID, blogContent, userID from tb_blog";

        int cnt = 0;

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();) {

            while(cnt++ != start){}
            cnt = 1;

            // 6. 遍历结果集
            while (rs.next() && cnt++<=number) {

                Blog blog = new Blog();
                blog.setBlogID(rs.getInt("blogID"));
                blog.setBlogContent(rs.getString("blogContent"));
                blog.setUserID(rs.getInt("userID"));
                list.add(blog);

            }

        } catch (SQLException e) {
            System.out.println("数据库查询过程中出现问题...");
        }

        return list;
    }
}
