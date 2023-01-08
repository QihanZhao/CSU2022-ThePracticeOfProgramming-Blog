package com.hanx.controller;

import com.hanx.dao.UserDAO;
import com.hanx.entity.MessageModel;
import com.hanx.entity.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
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

        Integer userID = jsonObj.getInt("userID");
        String userName = "";
        String userPassword = jsonObj.getString("userPassword");
        User userToQuery = new User(userID,userName,userPassword);
        MessageModel messageModel = new MessageModel();
        messageModel.setContent(userToQuery);

        User userQueried = UserDAO.findUser(userID);
        //用户名错误
        if(userQueried == null){
            messageModel.setMsg("UserNotExist");
        }
        //密码错误
        else if(!userPassword.equals(userQueried.getUserPassword())){
            messageModel.setMsg("WrongPassWord");
        }
        //登陆成功
        else{
            messageModel.setCode(1);
            messageModel.setMsg("LoginSuccess");
            messageModel.setContent(userQueried);
        }

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();
    }
}