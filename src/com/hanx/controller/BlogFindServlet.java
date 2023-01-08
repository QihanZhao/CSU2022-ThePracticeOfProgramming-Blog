package com.hanx.controller;

import com.hanx.dao.BlogDAO;
import com.hanx.dao.UserDAO;
import com.hanx.entity.Blog;
import com.hanx.entity.MessageModel;
import com.hanx.entity.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/findBlog")
public class BlogFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Blog blog = new Blog();
//        blog.setBlogContent(req.getParameter("blogContent"));
//        blog.setBlogID(Integer.parseInt(req.getParameter("blogID")));
//        blog.setUserID(Integer.parseInt(req.getParameter("userID")));
        //解析数据
        Integer blogID = Integer.parseInt(req.getParameter("blogID"));

        //处理数据
        MessageModel messageModel = new MessageModel();
        Blog blog = BlogDAO.findBlogByID(blogID);
        if(blog == null){
            messageModel.setMsg("BlogNotExist");
        } else{
            messageModel.setCode(1);
            messageModel.setMsg("FindSuccess");
            messageModel.setContent(blog);
        }

        //回传数据
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();
    }
}
