package edu.cd.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterSuccess extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>hello</title><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='content-type' content='text/html; charset=UTF-8'><!--<link rel='stylesheet' type='text/css' href='./styles.css'>--></head>");
        out.println("<body><h1>注册成功</h1>");
        // 添加返回登录界面的链接
        out.println("<p>恭喜你，注册成功！</p>");
        out.println("<a href='/UserManage/Login'>返回登录界面</a>");
        out.println("</body></html>");
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }
}
