package edu.cd.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 输出页面内容
		out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>用户管理</title>");
		out.println("<meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
		out.println("<link rel='stylesheet' type='text/css' href='css/1.css'>");
		out.println("</head><body>");
		out.println("<img src='imgs/conch.gif' width='150px' height='50px' />");
		out.println("<hr />");
		out.println("<h1>用户管理</h1>");
		out.println("<a href='/UserManage/UserController?type=getPageUsers'>管理用户</a><br />");
		out.println("<a href='/UserManage/UserController?type=gotoAddView'>添加用户</a><br />");
		out.println("<a href='/UserManage/Main?logout=true'>退出系统</a><br>");
		out.println("<hr />");
		out.println("<img src='imgs/fish.gif' width='150px' height='50px' />");
		out.println("</body></html>");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
