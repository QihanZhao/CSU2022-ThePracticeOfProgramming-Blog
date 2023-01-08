package com.hanx.controller;

import com.hanx.dao.UserDAO;
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
import java.util.List;

@WebServlet("/findAllUser")
public class UserAllFindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //处理数据
        MessageModel messageModel = new MessageModel();
        List<User> users = UserDAO.findAll();
        if(users.isEmpty()){
            messageModel.setMsg("NoUser");
        } else{
            messageModel.setCode(1);
            messageModel.setMsg("Success");
            messageModel.setContent(users);
        }

        //回传数据
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();
    }
}
