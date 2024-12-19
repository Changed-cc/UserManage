package edu.cd.controller;

import edu.cd.entity.Users;
import edu.cd.exception.IdIsNullException;
import edu.cd.service.UserService;
import edu.cd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            try {
                delUserById(request, response);
            } catch (IdIsNullException e) {
                throw new RuntimeException(e);
            }
            getAllUsers(request, response);
		} else if ("getPageUsers".equals(type)) {
			getPageUsers(request, response);
		} else if ("gotoAddView".equals(type)) {
			gotoAddView(request, response);
		} else if ("addUser".equals(type)) {
			addUser(request, response);
		} else if ("gotoUpdateUser".equals(type)) {
			gotoUpdateUser(request, response);
		} else if ("updateUserById".equals(type)) {
            try {
                updateUserById(request, response);
            } catch (IdIsNullException e) {
                throw new RuntimeException(e);
            }
        }
		out.flush();
		out.close();
	}

	private void gotoUpdateUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取用户的 ID
		Integer userId = Integer.parseInt(req.getParameter("id"));
		System.out.println(userId);
		// 将用户的 ID 设置为请求属性
		req.setAttribute("userId", userId);
		req.getRequestDispatcher("/Update").forward(req,res);
	}

	private void updateUserById(HttpServletRequest req, HttpServletResponse res) throws IdIsNullException, ServletException, IOException {
		System.out.println("updateUserById");
		//先接受各个参数
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickName");
		String pwd = req.getParameter("pwd");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birthday");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = dateFormat.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hobby = req.getParameter("hobby");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		int grade = Integer.parseInt(req.getParameter("grade"));
		String description = req.getParameter("description");
		String id = req.getParameter("userId");
		System.out.println(id);

		//再封装user
		Users users=null;
		Users user = new Users(name, nickName, pwd,gender, birthday, hobby, tel, email, grade, description);

		userService.updateUserById(users,Integer.parseInt(id));

		//getPageUsers(req,res);//走了两个控制器 -> 控制器链

		try {
			req.getRequestDispatcher("/Manage").forward(req,res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void gotoAddView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/AddView").forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			String name = request.getParameter("name");
			String nickName = request.getParameter("nickName");
			String pwd = request.getParameter("pwd");
			String gender = request.getParameter("gender");
			String birth = request.getParameter("birthday");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = null;
			try {
				birthday = dateFormat.parse(birth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String hobby = request.getParameter("hobby");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			int grade = Integer.parseInt(request.getParameter("grade"));
			String description = request.getParameter("description");

			Users user = new Users(name, nickName, pwd,gender, birthday, hobby, tel, email, grade, description);
			int result = userService.addUser(user);
			if (result > 0) {
				response.sendRedirect("/UserManage/UserController?type=getAllUsers");
			} else {
				response.getWriter().write("用户添加失败");
			}
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
		int recordCount = userService.getRecordCount();
		int pageCount = recordCount % pageSize == 0 ? recordCount / pageSize
				: recordCount / pageSize + 1;
		request.setAttribute("users", users);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageCount", pageCount);
		request.getRequestDispatcher("/Manage").forward(request, response);
	}

	private void getAllUsers(HttpServletRequest request,
							 HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Users> users = userService.getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/Manage").forward(request, response);
	}

	private void delUserById(HttpServletRequest req,
							 HttpServletResponse res) throws ServletException, IOException, IdIsNullException {

		String id = req.getParameter("id");
		userService.delUserById(Integer.parseInt(id));
		getPageUsers(req,res);//走了两个控制器 -> 控制器链
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}