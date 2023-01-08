package com.hanx.controller;

import com.hanx.dao.ExpressDAO;
import com.hanx.entity.Express;
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

@WebServlet("/findAllExpress")
public class ExpressAllFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //处理数据
        MessageModel messageModel = new MessageModel();
        List<Express> expresses = ExpressDAO.findAllExpress();
        if(expresses == null){
            messageModel.setMsg("BlogNotExist");
        } else{
            messageModel.setCode(1);
            messageModel.setMsg("FindSuccess");
            messageModel.setContent(expresses);
        }
        System.out.println(new JSONObject(messageModel));

        //回传数据
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(messageModel));
        out.flush();
    }
}
