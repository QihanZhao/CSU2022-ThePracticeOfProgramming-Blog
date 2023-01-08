package com.hanx.controller;

import com.hanx.dao.BlogDAO;
import com.hanx.entity.Blog;
import com.hanx.entity.MessageModel;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addBlog")
public class BlogAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解析数据
        req.setCharacterEncoding("UTF-8");
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }catch(Exception e) { e.printStackTrace();}
        JSONObject jsonObj = new JSONObject(json.toString());
        System.out.println(jsonObj);

        Blog blog = new Blog();
        blog.setBlogContent(jsonObj.getString("blogContent"));
        blog.setBlogID(0);
        blog.setUserID(jsonObj.getInt("userID"));

        //处理数据
        MessageModel messageModel = new MessageModel();
        if(BlogDAO.addBlog(blog) == 1){
            messageModel.setCode(1);
            messageModel.setMsg("AddSuccess");
        }

        //回传数据
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();

    }
}
