package edu.cd.view;

import edu.cd.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Manage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        ArrayList<Users> users=(ArrayList<Users>) request.getAttribute("users");

        out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>login</title>");
        out.println("<meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
        out.println("<!--<link rel='stylesheet' type='text/css' href='./styles.css'>-->");
        out.println("</head><body><h1>用户管理</h1>");

        out.println("<table>");
        out.println("<tr>"
                + "<th>用户名</th>"
                + "<th>昵称</th>"
                + "<th>性别</th>"
                + "<th>生日</th>"
                + "<th>爱好</th>"
                + "<th>电话</th>"
                + "<th>邮箱</th>"
                + "<th>等级</th>"
                + "<th>描述</th>"
                + "<th>操作</th>"
                + "</tr>");

        if(users!=null&&users.size()>0){
            for (int i = 0; i < users.size(); i++) {
                Users user=users.get(i);
                out.println("<tr>"
                        + "<td>"+user.getName()+"</td>"
                        + "<td>"+user.getNickName()+"</td>"
                        + "<td>"+user.getGender()+"</td>"
                        + "<td>"+user.getBirthday()+"</td>"
                        + "<td>"+user.getHobby()+"</td>"
                        + "<td>"+user.getTel()+"</td>"
                        + "<td>"+user.getEmail()+"</td>"
                        + "<td>"+user.getGrade()+"</td>"
                        + "<td>"+user.getDescription()+"</td>"
                        + "<td><a href='??'>删除</a><a href='??'>修改</a></td>"
                        + "</tr>");
            }
        }
        out.println("</table>");

        out.println("</body></html>");

        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}

