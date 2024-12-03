package edu.cd.controller;
import edu.cd.entity.Users;
import edu.cd.util.Base64Util;
import edu.cd.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		// 获取参数
		String name = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		System.out.println("name=" + name);
		System.out.println("pwd=" + pwd);

		try {
			// 登录验证(database)
			// 1.加载驱动

			// 2.获取数据库连接
			Connection conn = JdbcUtil.getConn();
			// 3.创建表示sql的语句对象
			// Statement stmt = conn.createStatement();
			String sql = "select * from users where name=? and pwd=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.执行sql，如果有结果，写入结果集
			// System.out.println(sql);
			// 给？赋值
			ps.setString(1, name);
			ps.setString(2, Base64Util.encode(pwd));
			ResultSet rs = ps.executeQuery();
			// 5.遍历结果集
			// 封装user

			if (rs.next()) {

				Users user = new Users();

				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setNickName(rs.getString("nickName"));
				user.setPwd(rs.getString("pwd"));
				user.setGender(rs.getString("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setHobby(rs.getString("hobby"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setGrade(rs.getInt("grade"));
				user.setDescription(rs.getString("description"));

				// succ
				request.getRequestDispatcher("/Main")
						.forward(request, response);

			} else {

				// fail
				String errMsg = "用户名或密码错误，请重新输入！";
				request.setAttribute("errMsg", errMsg);

				request.getRequestDispatcher("/Login").forward(request,
						response);
			}
			// 6.关闭资源
			JdbcUtil.release(conn, ps, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}


