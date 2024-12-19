package edu.cd.Http;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HttpDemo extends HttpServlet {
    boolean first = true;
    String lastTime="";
    private static Set<String> onlineUsers = new HashSet<>();
    //显示用户最近一次登陆时间 （考虑第一次登录）
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");  //从请求参数中获取用户名
        if(username != null && !username.isEmpty()) {
            Cookie usernameCookie = new Cookie("username", username);
            res.addCookie(usernameCookie);
        }
        Cookie[] cookies = req.getCookies();
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie : cookies){
                if("username".equals(cookie.getName())){
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if(!username.isEmpty()){
            out.write("你好，欢迎回来，" + username + "!<br>");
            onlineUsers.add(username); // 添加到在线用户集合
        }

        //第一次
        if(first){
            first = false;
            Cookie cookie = new Cookie("lastTime",new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").format(new Date()));
            //服务器返回浏览器
            cookie.setMaxAge(7*24*60*60); //生命周期，0表示删除
            res.addCookie(cookie); //服务器给浏览器一个指示，让浏览器把这个cookie保存起来
        }else {
            //不是第一次，读出来修改
            Cookie[] cookies1 = req.getCookies();//返回一个cookie数组
            if(cookies1!=null&&cookies1.length>0){
                for(Cookie cookie:cookies1){
                    if("lastTime".equals(cookie.getName())){
                        //first = false;
                        lastTime=cookie.getValue();//上次的值
                        cookie.setValue(new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").format(new Date()));//这次的值记录到cookie
                        res.addCookie(cookie);//浏览器保存
                        break;
                    }
                }
            }
        }

        if("".equals(lastTime)){
            out.write("你好，你是第一次登录！<br>");

        }else {
            out.write("你好，你上次登录的时间是"+lastTime+"！<br>");
        }

        int visitCount = 0;
        Cookie visitCountCookie = null;

        Cookie[] cookies2 = req.getCookies();
        if(cookies2!=null && cookies2.length>0){
            for(Cookie cookie : cookies){
                if("visitCount".equals(cookie.getName())){
                    visitCountCookie = cookie;
                    break;
                }
            }
        }

        if(visitCountCookie != null){
            visitCount = Integer.parseInt(visitCountCookie.getValue());
        }
        visitCount++;

        visitCountCookie = new Cookie("visitCount", String.valueOf(visitCount));
        visitCountCookie.setMaxAge(7*24*60*60);
        res.addCookie(visitCountCookie);

        out.write("网站已被访问了 " + visitCount + " 次!<br>");
        out.write("当前在线用户人数：" + onlineUsers.size() + "<br>");
        out.write("在线用户列表：<br>");
        for(String user : onlineUsers){
            out.write(user + "<br>");
        }
        out.write("<a href='/UserManage/Main'>进入用户管理</a><br>");
        out.write("<a href='/UserManage/HttpDemo?logout=true'>安全退出</a><br>");
        String logout = req.getParameter("logout");
        if ("true".equals(logout)) {
            // 清除会话
            req.getSession().invalidate();
            // 从在线用户集合中删除用户
            onlineUsers.remove(username);
            // 请求转发到登录页面或其他适当的页面
            req.getRequestDispatcher("/Login").forward(req, res);
            return;
        }
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }
}