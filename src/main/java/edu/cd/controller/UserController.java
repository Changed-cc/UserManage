package edu.cd.controller;

import edu.cd.entity.Users;
import edu.cd.service.UserService;
import edu.cd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserController extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");

		if ("getAllUsers".equals(type)) {
			getAllUsers(request, response);
		} else if ("delUserById".equals(type)) {
			delUserById(request, response);
			getAllUsers(request, response);
		} else if ("getPageUsers".equals(type)) {
			getPageUsers(request, response);
		}

		out.flush();
		out.close();
	}

	private void getPageUsers(HttpServletRequest request,
							  HttpServletResponse response) throws ServletException, IOException {

		int pageNow = 1, pageSize = 3;

		String pn = request.getParameter("pageNow");
		String ps = request.getParameter("pageSize");

		if (pn != null)
			pageNow = Integer.parseInt(pn);
		if (ps != null)
			pageSize = Integer.parseInt(ps);

		ArrayList<Users> users = userService.getPageUsers(pageNow, pageSize);
		request.setAttribute("users", users);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("/Manage").forward(request, response);
	}

	private void getAllUsers(HttpServletRequest request,
							 HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Users> users = userService.getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/Manage").forward(request, response);
	}

	private void delUserById(HttpServletRequest request,
							 HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");

		try {
			int res = userService.delUserById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}


}