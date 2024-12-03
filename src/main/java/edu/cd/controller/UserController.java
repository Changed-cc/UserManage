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

		ArrayList<Users> users = userService.getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/Manage").forward(request, response);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}