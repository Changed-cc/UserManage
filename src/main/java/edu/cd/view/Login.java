package edu.cd.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String errMsg = (String) request.getAttribute("errMsg");

		out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>login</title>");
		out.println("<meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
		out.println("<!--<link rel='stylesheet' type='text/css' href='./styles.css'>-->");
		out.println("</head><body><h1>用户登录</h1>");
		out.println("<form action='/UserManage/LoginController' method='post'>");
		out.println("<label for='username'>用户名：</label><input type='text' name='user' id='username' /><br />");
		out.println("<label for='password'>密&nbsp;&nbsp;&nbsp;码：</label><input type='password' name='pwd' id='password' /><br />");
		out.println("<input type='submit' value='登录' /><input type='reset' value='重置' />");
		out.println("<button type=\"button\" onclick=\"goToRegister()\">注册</button>");
		out.println("</form>");
		out.println("<script type='text/javascript'>");
		out.println("function goToRegister() {");
		out.println("  window.location.href = '/UserManage/Register';");
		out.println("}");
		out.println("</script>");
		if (errMsg != null)
			out.println("<span>" + errMsg + "</span>");
		out.println("</body></html>");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
