package edu.cd.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class Pay extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		UUID uuid=UUID.randomUUID();
		String token=uuid+"";
		
		request.getSession().setAttribute("token", token);
		
		out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head><title>login</title>");
		out.println("<meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
		out.println("<!--<link rel='stylesheet' type='text/css' href='./styles.css'>-->");
		out.println("</head><body><h1>支付</h1>");
		out.println("<form action='/UserManage3/PayServlet' method='post'>");
		out.println("<label for='money'>金额：</label><input type='text' name='money'/><br />");
		out.println("<input type='hidden' name='token' value='"+token+"' />");
		out.println("<input type='submit' value='付款' />");
		out.println("</form>");
		out.println("</body></html>");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
