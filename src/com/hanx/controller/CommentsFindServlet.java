package com.hanx.controller;

import com.hanx.dao.BlogDAO;
import com.hanx.dao.CommentDAO;
import com.hanx.entity.Blog;
import com.hanx.entity.Comment;
import com.hanx.entity.MessageModel;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/findComments")
public class CommentsFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解析数据
        Integer blogID = Integer.parseInt(req.getParameter("blogID"));

        //处理数据
        List<Comment> comments = CommentDAO.findCommentsByBlogID(blogID);
        MessageModel messageModel = new MessageModel();
        if(comments.isEmpty()){
            messageModel.setMsg("NoComment");
        } else{
            messageModel.setCode(1);
            messageModel.setMsg("FindSuccess");
            messageModel.setContent(comments);
        }

        //回传数据
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();
    }
}
