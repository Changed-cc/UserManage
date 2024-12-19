package edu.cd.view.ManageAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddView extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>添加用户</title>");
        out.println("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
        out.println("</head><body>");
        out.println("<h1>添加用户</h1>");
        out.println("<form action='/UserManage/UserController?type=addUser' method='post'>");
        out.println("用户名: <input type='text' name='name' required><br>");
        out.println("昵称: <input type='text' name='nickName' required><br>");
        out.println("性别: <input type='radio' name='gender' value='1' checked>男 <input type='radio' name='gender' value='2'>女<br>");
        out.println("生日: <input type='date' name='birthday' required><br>");
        out.println("爱好: <input type='text' name='hobby'><br>");
        out.println("电话: <input type='text' name='tel'><br>");
        out.println("邮箱: <input type='text' name='email'><br>");
        out.println("等级: <input type='number' name='grade' min='1' max='5' value='1'><br>");
        out.println("描述: <textarea name='description'></textarea><br>");
        out.println("<input type='submit' value='添加用户'>");
        out.println("</form>");
        out.println("</body></html>");
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}

