package edu.cd.controller;

import edu.cd.util.Base64Util;
import edu.cd.util.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        req.setCharacterEncoding("utf-8");

        // 获取用户输入的注册信息
        String username = req.getParameter("user");
        String nickName = req.getParameter("nickName");
        String pwd = req.getParameter("pwd");
        String spwd = req.getParameter("spwd");
        String gender = req.getParameter("gender");
        String birthdayStr = req.getParameter("birthday"); // 获取生日字符串
        String hobby = req.getParameter("hobby");
        String tel = req.getParameter("tel");
        String email = req.getParameter("email");
        int grade = Integer.parseInt(req.getParameter("grade"));
        String description = req.getParameter("description");

        // 使用 SimpleDateFormat 解析生日字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = sdf.parse(birthdayStr); // 解析日期字符串
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMsg", "日期格式不正确，请使用 yyyy-MM-dd 格式！");
            req.getRequestDispatcher("/Register").forward(req, res);
            return; // 终止后续操作
        }

        // 验证用户名和密码
        if (validateInputs(username, pwd, spwd)) {
            // 注册成功
            if (registerUser(username, nickName, pwd, gender, birthday, hobby, tel, email, grade, description)) {
                System.out.println(username);
                System.out.println(pwd);
                req.getRequestDispatcher("/RegisterSuccess").forward(req, res);
            } else {
                // 注册失败
                String errMsg = "注册失败，请重试！";
                req.setAttribute("errMsg", errMsg);
                req.getRequestDispatcher("/Register").forward(req, res);
            }
        } else {
            // 输入不合法
            String errMsg = "用户名或密码不合法，请重新输入！";
            req.setAttribute("errMsg", errMsg);
            req.getRequestDispatcher("/Register").forward(req, res);
        }

        out.flush();
        out.close();
    }

    private boolean validateInputs(String username, String pwd, String spwd) {
        // 使用正则表达式验证各个字段的合法性
        String usernameRegex = "^[\\u4E00-\\u9FA5A-Za-z0-9_-]{2,16}$"; //允许中文字符、字母、数字、下划线和短横线，并且长度在2到16之间
        String passwordRegex = "^[a-zA-Z0-9_-]{3,18}$";
        return username.matches(usernameRegex) && pwd.matches(passwordRegex) && spwd.equals(pwd) && spwd != null;
    }

    private boolean registerUser(String username, String nickName, String password, String gender, Date birthday, String hobby, String tel, String email, Integer grade, String description) {
        boolean success = false;
        try {
            // 创建SQL语句
            String sql = "insert into users (name, nickName, pwd, gender, birthday, hobby, tel, email, grade, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // 准备参数数组
            Object[] params = {username, nickName, Base64Util.encode(password), gender, birthday, hobby, tel, email, grade, description};

            // 执行更新操作
            int res = DBHelper.executeUpdate(sql, params);

            // 注册成功的条件是影响的行数大于0
            if (res > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }
}
