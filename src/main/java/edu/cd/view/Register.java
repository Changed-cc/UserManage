package edu.cd.view;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Register.java
public class Register extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();

        String errMsg = (String) req.getAttribute("errMsg");

        out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>hello</title><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='content-type' content='text/html; charset=UTF-8'><!--<link rel='stylesheet' type='text/css' href='./styles.css'>--></head>");
        out.println("<body><h1>用户注册</h1>");

        // 给form标签加上 id="registerForm"
        out.println("<form  action='/UserManage/RegisterController' method='get'>");
        out.println("<label for='username' >用&nbsp;户&nbsp;名：</label>");
        out.println("<input type='text' name='user' id='username'/><br />");
        out.println("<label for='nickName' >昵&nbsp;&nbsp;&nbsp;&nbsp;称：</label>");
        out.println("<input type='text' name='nickName' id='nickName'/><br />");
        out.println("<label for='pass' >密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>");
        out.println("<input type='password' name='pwd' id='pass'/><br />");
        out.println("<label for='spass' >确认密码：</label>");
        out.println("<input type='password' name='spwd' id='spass'/><br />");
        // 新添加的注册信息
        out.println("<label>性别：</label>");
        out.println("<input type='radio' name='gender' value='0' id='male'> <label for='male'>男性</label>");
        out.println("<input type='radio' name='gender' value='1' id='female'> <label for='female'>女性</label><br />");
        out.println("<label for='birthday'>生日：</label>");
        out.println("<input type='text' name='birthday' id='birthday'/><br />");
        out.println("<label for='hobby'>爱好：</label>");
        out.println("<input type='text' name='hobby' id='hobby'/><br />");
        out.println("<label for='tel'>电话：</label>");
        out.println("<input type='text' name='tel' id='tel'/><br />");
        out.println("<label for='email'>电子邮件：</label>");
        out.println("<input type='text' name='email' id='email'/><br />");
        out.println("<label for='grade'>成绩：</label>");
        out.println("<input type=\"text\" name=\"grade\" id=\"grade\" pattern=\"\\d*\" title=\"请输入数字\" required /><br />");
        out.println("<label for='description'>描述：</label>");
        out.println("<textarea name='description' id='description'></textarea><br />");
        out.println("<input type='submit' value='提交' /><input type='reset' value='重置' />");

        if (errMsg != null)
            out.println("<p>" + errMsg + "</p>");
        out.println("</form></body></html>");
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }
}