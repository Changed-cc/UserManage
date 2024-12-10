package edu.cd.controller;

import edu.cd.service.UserService;
import edu.cd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		// 获取参数
		String name = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		// System.out.println("name="+name);
		// System.out.println("pwd="+pwd);

		// 登录验证(database)

		if (userService.checkUser(name, pwd) == true) {
			// succ
			request.getRequestDispatcher("/Main").forward(request, response);
		} else {
			// fail
			String errMsg = "用户名或密码错误，请重新输入！";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("/Login").forward(request, response);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}


