package edu.cd.view.ManageAction;
import edu.cd.entity.Users;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
public class Update extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取要修改的用户信息
        Users user = (Users) request.getAttribute("user");

        out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>Update User</title>");
        out.println("<meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
        out.println("<link rel='stylesheet' type='text/css' href='css/1.css'>");
        out.println("<script type='text/javascript' src='js/1.js'></script>");
        out.println("</head><body>");

        out.println("<h1>修改用户信息</h1>");
        out.println("<form action='/UserManage/UserController?type=updateUserById' method='post'>");
        out.println("<input type='hidden' name='userId' value='" + (user != null ? user.getId() : "") + "' />");
        out.println("<label>用户名:</label><input type='text' name='name' value='" + (user != null ? user.getName() : "") + "' /><br />");
        out.println("<label>昵称:</label><input type='text' name='nickName' value='" + (user != null ? user.getNickName() : "") + "' /><br />");
        out.println("<label>密码:</label><input type='password' name='pwd' value='" + (user != null ? user.getPwd() : "") + "' /><br />");
        out.println("<label>性别:</label>");
        out.println("<select name='gender'>");
        out.println("<option value='1' " + (user != null && "1".equals(user.getGender()) ? "selected" : "") + ">男</option>");
        out.println("<option value='2' " + (user != null && "2".equals(user.getGender()) ? "selected" : "") + ">女</option>");
        out.println("</select><br />");
        out.println("<label>生日:</label><input type='date' name='birthday' value='" + (user != null ? user.getBirthday() : "") + "' /><br />");
        out.println("<label>爱好:</label><input type='text' name='hobby' value='" + (user != null ? user.getHobby() : "") + "' /><br />");
        out.println("<label>电话:</label><input type='text' name='tel' value='" + (user != null ? user.getTel() : "") + "' /><br />");
        out.println("<label>邮箱:</label><input type='email' name='email' value='" + (user != null ? user.getEmail() : "") + "' /><br />");
        out.println("<label>等级:</label><input type='number' name='grade' value='" + (user != null ? user.getGrade() : "") + "' /><br />");
        out.println("<label>描述:</label><textarea name='description'>" + (user != null ? user.getDescription() : "") + "</textarea><br />");
        out.println("<input type='submit' value='保存' />");
        out.println("<input type='reset' value='重置' />");
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
