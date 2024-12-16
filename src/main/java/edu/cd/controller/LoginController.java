package edu.cd.controller;

import edu.cd.service.UserService;
import edu.cd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		// 显示用户最后一次的访问时间

		String lastTime = "", msg = "";
		boolean isFirst = true;

		if (isFirst == true) {
			Cookie cookie = new Cookie("lastTime", new SimpleDateFormat(
					"yyyy-MM-dd.HH:mm:ss").format(new Date()));
			cookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(cookie);
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("lastTime".equals(cookie.getName())) {
					isFirst = false;
					lastTime = cookie.getValue();
					cookie.setValue(new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss")
							.format(new Date()));
					// cookie.setMaxAge(7*24*60*60);
					response.addCookie(cookie);
					break;
				}
			}
		}

		if (isFirst == true) {
			msg = "你好，你是第一次登录。";
		} else {
			msg = "你好，你上一次登录的时间是" + lastTime + "。";
		}
		// 登录验证(database)

		if (userService.checkUser(name, pwd) == true) {
			// succ
			request.setAttribute("lastTime", msg);
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


