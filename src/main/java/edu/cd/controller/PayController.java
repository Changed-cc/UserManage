package edu.cd.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PayController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
//		session.invalidate();
        String sessionToken = (String) session.getAttribute("token");
        String formToken = request.getParameter("token");

        System.out.println("sessionToken=" + sessionToken);
        System.out.println("formToken=" + formToken);

        String money = request.getParameter("money");

        if (formToken.equals(sessionToken)) {
            System.out.println("支付了" + money + "元。");
            session.removeAttribute("token");
        } else {
            System.out.println("对不起，请不要重复提交。");
        }

        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
