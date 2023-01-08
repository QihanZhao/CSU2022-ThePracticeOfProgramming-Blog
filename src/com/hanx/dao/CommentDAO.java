package com.hanx.dao;

import com.hanx.entity.Blog;
import com.hanx.entity.Comment;
import com.hanx.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public static int addComment(Comment comment){
        int cnt = 0;
        String sql = "insert into tb_comment (commentContent,userID,blogID)"+
                "values(?,?,?)";
        try(
                Connection conn = JdbcUtil.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
        ){
//            pstm.setInt(1,comment.getCommentID());
            pstm.setString(1,comment.getCommentContent());
            pstm.setInt(2,comment.getUserID());
            pstm.setInt(3,comment.getBlogID());
            cnt = pstm.executeUpdate();
        } catch(SQLException e) {
            System.out.println("数据库增加过程中出现问题...");
        }
        return cnt;
    }

    public static List<Comment> findCommentsByBlogID(Integer blogID) {
        List<Comment> list = new ArrayList<>();
        // SQL语句
        String sql = "select commentID,commentContent,userID,blogID from tb_comment where blogID = " + blogID;

        try (   // 2.创建数据库连接
                Connection conn = JdbcUtil.getConnection();
                // 3.创建语句对象
                PreparedStatement pstm = conn.prepareStatement(sql);
                // 4.执行查询
                ResultSet rs = pstm.executeQuery();) {

            // 6. 遍历结果集
            while (rs.next() ) {

                Comment comment = new Comment();
                comment.setCommentID(rs.getInt("commentID"));
                comment.setCommentContent(rs.getString("commentContent"));
                comment.setUserID(rs.getInt("userID"));
                comment.setBlogID(rs.getInt("blogID"));

                list.add(comment);

            }

        } catch (SQLException e) {
            System.out.println("数据库查询过程中出现问题...");
        }

        return list;
    }
}
